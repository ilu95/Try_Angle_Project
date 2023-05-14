package org.tensorflow.lite.examples.objectdetection

import android.content.Context
import android.graphics.Bitmap
import android.os.SystemClock
import android.util.Log
import org.tensorflow.lite.gpu.CompatibilityList
import org.tensorflow.lite.support.image.ImageProcessor
import org.tensorflow.lite.support.image.ops.Rot90Op
import org.tensorflow.lite.task.core.BaseOptions
import org.tensorflow.lite.task.vision.detector.Detection
import org.tensorflow.lite.task.vision.detector.ObjectDetector

import androidx.camera.core.ImageAnalysis
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.core.CameraSelector
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

import android.graphics.ImageFormat
import android.graphics.Rect
import android.graphics.YuvImage
import android.media.Image
import java.io.ByteArrayOutputStream

import android.graphics.BitmapFactory
import androidx.lifecycle.LifecycleOwner

import androidx.camera.core.ImageProxy
//import androidx.camera.core.ExperimentalGetImage
import kotlin.math.abs
import org.tensorflow.lite.support.image.TensorImage

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint

object ImageUtils {
    fun imageToNV21(image: Image): ByteArray {
        val yBuffer = image.planes[0].buffer
        val uBuffer = image.planes[1].buffer
        val vBuffer = image.planes[2].buffer

        val ySize = yBuffer.remaining()
        val uSize = uBuffer.remaining()
        val vSize = vBuffer.remaining()

        val nv21 = ByteArray(ySize + uSize + vSize)

        yBuffer.get(nv21, 0, ySize)
        vBuffer.get(nv21, ySize, vSize)
        uBuffer.get(nv21, ySize + vSize, uSize)

        return nv21
    }
}



class ObjectDetectorHelper(
  var threshold: Float = 0.5f,
  var numThreads: Int = 2,
  var maxResults: Int = 3,
  var currentDelegate: Int = 0,
  var currentModel: Int = 0,
  val context: Context,
  private var lastResults: MutableList<Detection>? = null,
  private var lastInferenceTime: Long = 0,
  private var lastImageHeight: Int = 0,
  private var lastImageWidth: Int = 0,

  val objectDetectorListener: DetectorListener?,
  private val centerThreshold: Float = 50f


) {

    // For this example this needs to be a var so it can be reset on changes. If the ObjectDetector
    // will not change, a lazy val would be preferable.
    private var objectDetector: ObjectDetector? = null
    private var cameraExecutor: ExecutorService
    private var imageAnalysis: ImageAnalysis


    init {
        setupObjectDetector()
        cameraExecutor = Executors.newSingleThreadExecutor()
        imageAnalysis = ImageAnalysis.Builder()
            .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
            .build()

    }

    fun clearObjectDetector() {
        objectDetector = null
    }

    fun getResults(): Triple<MutableList<Detection>?, Long, Pair<Int, Int>> {
        return Triple(lastResults, lastInferenceTime, Pair(lastImageHeight, lastImageWidth))
    }


    // Initialize the object detector using current settings on the
    // thread that is using it. CPU and NNAPI delegates can be used with detectors
    // that are created on the main thread and used on a background thread, but
    // the GPU delegate needs to be used on the thread that initialized the detector
    fun setupObjectDetector() {
        // Create the base options for the detector using specifies max results and score threshold
        val optionsBuilder =
            ObjectDetector.ObjectDetectorOptions.builder()
                .setScoreThreshold(threshold)
                .setMaxResults(maxResults)

        // Set general detection options, including number of used threads
        val baseOptionsBuilder = BaseOptions.builder().setNumThreads(numThreads)

        // Use the specified hardware for running the model. Default to CPU
        when (currentDelegate) {
            DELEGATE_CPU -> {
                // Default
            }
            DELEGATE_GPU -> {
                if (CompatibilityList().isDelegateSupportedOnThisDevice) {
                    baseOptionsBuilder.useGpu()
                } else {
                    objectDetectorListener?.onError("GPU is not supported on this device")
                }
            }
            DELEGATE_NNAPI -> {
                baseOptionsBuilder.useNnapi()
            }
        }

        optionsBuilder.setBaseOptions(baseOptionsBuilder.build())

        val modelName =
            when (currentModel) {
                MODEL_MOBILENETV1 -> "mobilenetv1.tflite"
                MODEL_EFFICIENTDETV0 -> "efficientdet-lite0.tflite"
                MODEL_EFFICIENTDETV1 -> "efficientdet-lite1.tflite"
                MODEL_EFFICIENTDETV2 -> "efficientdet-lite2.tflite"
                else -> "mobilenetv1.tflite"
            }

        try {
            objectDetector =
                ObjectDetector.createFromFileAndOptions(context, modelName, optionsBuilder.build())
        } catch (e: IllegalStateException) {
            objectDetectorListener?.onError(
                "Object detector failed to initialize. See error logs for details"
            )
            Log.e("Test", "TFLite failed to load model with error: " + e.message)
        }
    }

    fun detect(image: Bitmap, imageRotation: Int) {
        if (objectDetector == null) {
            setupObjectDetector()
        }

        // Inference time is the difference between the system time at the start and finish of the
        // process
        var inferenceTime = SystemClock.uptimeMillis()

        // Create preprocessor for the image.
        // See https://www.tensorflow.org/lite/inference_with_metadata/
        //            lite_support#imageprocessor_architecture
        val imageProcessor =
            ImageProcessor.Builder()
                .add(Rot90Op(-imageRotation / 90))
                .build()

        // Preprocess the image and convert it into a TensorImage for detection.
        val tensorImage = imageProcessor.process(TensorImage.fromBitmap(image))

        val results = objectDetector?.detect(tensorImage)
        inferenceTime = SystemClock.uptimeMillis() - inferenceTime


        objectDetectorListener?.onResults(
            results,
            inferenceTime,
            tensorImage.height,
            tensorImage.width)


        // Check if the detections list is not empty before accessing the first object
        if (results != null && results.isNotEmpty()) {
            val screenCenterX = tensorImage.width.toFloat() / 2f
            val screenCenterY = tensorImage.height.toFloat() / 2f

            val detectedObject = results[0]
            val boundingBox = detectedObject.boundingBox
            val objectCenterX = (boundingBox.left + boundingBox.right) / 2f
            val objectCenterY = (boundingBox.top + boundingBox.bottom) / 2f

            val deltaX = screenCenterX - objectCenterX
            val deltaY = screenCenterY - objectCenterY

            // Check if the object is centered within the threshold
            val isCentered = abs(deltaX) <= centerThreshold && abs(deltaY) <= centerThreshold
            objectDetectorListener?.onObjectCentered(isCentered)

        }


        lastResults = results
        lastInferenceTime = inferenceTime
        lastImageHeight = tensorImage.height
        lastImageWidth = tensorImage.width
    }



    @androidx.camera.core.ExperimentalGetImage
    class AnnotatedImageAnalyzer(
        private val helper: ObjectDetectorHelper,
    ) : ImageAnalysis.Analyzer {

        private fun Image.imageToBitmap(): Bitmap {
            val nv21 = ImageUtils.imageToNV21(this)
            val yuvImage = YuvImage(
                nv21,
                ImageFormat.NV21,
                width,
                height,
                null
            )
            val out = ByteArrayOutputStream()
            yuvImage.compressToJpeg(Rect(0, 0, width, height), 100, out)
            val byteArray = out.toByteArray()
            return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
        }

        private fun drawBoundingBox(bitmap: Bitmap, detection: Detection): Bitmap {
            val newBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true)
            val canvas = Canvas(newBitmap)
            val paint = Paint().apply {
                color = Color.RED
                strokeWidth = 4f
                style = Paint.Style.STROKE
            }

            canvas.drawRect(detection.boundingBox, paint)
            return newBitmap
        }

        @androidx.camera.core.ExperimentalGetImage
        override fun analyze(imageProxy: ImageProxy) {
            val rotationDegrees = imageProxy.imageInfo.rotationDegrees
            val image: Image = imageProxy.image ?: return
            val bitmap = image.imageToBitmap() // Use the extension function

            helper.detect(bitmap, rotationDegrees)
            val (results, _, imageSize) = helper.getResults()
            val (imageHeight, imageWidth) = imageSize

            val detected = results?.isNotEmpty() ?: false
            helper.objectDetectorListener?.onObjectDetected(detected)

            results?.firstOrNull()?.let { detection ->
                val boundingBox = detection.boundingBox
                val bitmapWithBoundingBox = drawBoundingBox(bitmap, detection)
                val objectCenterX = (boundingBox.left + boundingBox.right) / 2f
                val objectCenterY = (boundingBox.top + boundingBox.bottom) / 2f

                // 화면 중앙 좌표 계산
                val screenCenterX = imageWidth.toFloat() / 2f
                val screenCenterY = imageHeight.toFloat() / 2f

                // 객체가 중앙에서 벗어난 경우 이동해야 하는 값을 계산
                val deltaX = screenCenterX - objectCenterX
                val deltaY = screenCenterY - objectCenterY

                // Pass the annotated image to the listener
                helper.objectDetectorListener?.onAnnotatedImage(bitmapWithBoundingBox)

                // 중앙에서 벗어난 정도를 확인하고, 임계값을 초과한 경우에만 onObjectCenterDelta를 호출합니다.
                if (abs(deltaX) > helper.centerThreshold || abs(deltaY) > helper.centerThreshold) {
                    Log.d("Test", "Calling onObjectCenterDelta: deltaX=$deltaX, deltaY=$deltaY")
                    helper.objectDetectorListener?.onObjectCenterDelta(deltaX, deltaY)
                } else {
                    // 객체가 중앙에 위치한 경우, deltaX와 deltaY를 0으로 설정합니다.
                    helper.objectDetectorListener?.onObjectCenterDelta(0f, 0f)
                }
            }

            imageProxy.close()
        }



    }


    @androidx.camera.core.ExperimentalGetImage
    fun analyzeImage(
        cameraProvider: ProcessCameraProvider,
        cameraSelector: CameraSelector,
        lifecycleOwner: LifecycleOwner,
    ) {
        val imageAnalysis = ImageAnalysis.Builder()
            .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
            .build()

        // AnnotatedImageAnalyzer를 사용하도록 변경
        imageAnalysis.setAnalyzer(cameraExecutor, AnnotatedImageAnalyzer(this))

        cameraProvider.unbindAll()
        cameraProvider.bindToLifecycle(lifecycleOwner, cameraSelector, imageAnalysis)
    }






    interface DetectorListener {
        abstract val previewView: Any

        fun onError(error: String)
        fun onResults(results: MutableList<Detection>?, inferenceTime: Long, imageHeight: Int, imageWidth: Int)
        fun onObjectCenterDelta(deltaX: Float, deltaY: Float)
        fun onObjectDetected(detected: Boolean) // 이 줄을 추가하세요
        fun onObjectCentered(isCentered: Boolean)
        fun onAnnotatedImage(annotatedImage: Bitmap)
    }

    companion object {
        const val DELEGATE_CPU = 0
        const val DELEGATE_GPU = 1
        const val DELEGATE_NNAPI = 2
        const val MODEL_MOBILENETV1 = 0
        const val MODEL_EFFICIENTDETV0 = 1
        const val MODEL_EFFICIENTDETV1 = 2
        const val MODEL_EFFICIENTDETV2 = 3
    }
}
