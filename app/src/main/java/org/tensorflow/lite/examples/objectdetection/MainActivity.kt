package org.tensorflow.lite.examples.objectdetection

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.CameraSelector
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import org.tensorflow.lite.examples.objectdetection.databinding.ActivityMainBinding
import org.tensorflow.lite.task.vision.detector.Detection
import kotlin.math.abs
import android.widget.Toast
import android.graphics.Bitmap
import org.tensorflow.lite.examples.objectdetection.fragments.CameraFragment

class MainActivity : AppCompatActivity(), ObjectDetectorHelper.DetectorListener  {

    // CameraFragment를 저장하는 속성을 추가합니다.
    private lateinit var cameraFragment: CameraFragment
    // previewView 속성을 수정하여 CameraFragment의 viewFinder를 반환하도록 변경합니다.
    override val previewView: Any
        get() = cameraFragment.viewFinder

    private lateinit var objectDetectorHelper: ObjectDetectorHelper

    private lateinit var activityMainBinding: ActivityMainBinding

    override fun onObjectCenterDelta(deltaX: Float, deltaY: Float) {
        // 여기에서 deltaX, deltaY를 처리하십시오.
        Log.d("MainActivity", "onObjectCenterDelta called")
        // 이동 거리의 임계값을 설정합니다.
        val threshold = 0.1f

        // deltaX와 deltaY의 절댓값이 임계값보다 큰지 확인합니다.
        if (abs(deltaX) > threshold || abs(deltaY) > threshold) {
            val deltaXPercentage = (deltaX * 100).toInt()
            val deltaYPercentage = (deltaY * 100).toInt()
            Toast.makeText(applicationContext, "객체가 중앙에서 벗어남: X축: $deltaXPercentage%, Y축: $deltaYPercentage%", Toast.LENGTH_SHORT).show()
        }
        // 예를 들어, 로그를 출력하거나 UI를 업데이트할 수 있습니다.
        Log.d("ObjectCenterDelta", "Delta X: $deltaX, Delta Y: $deltaY")
    }

    override fun onAnnotatedImage(annotatedImage: Bitmap) {
        runOnUiThread {
            activityMainBinding.imageView.setImageBitmap(annotatedImage)
        }
    }


    override fun onObjectDetected(detected: Boolean) {
        runOnUiThread {
            if (detected) {
                Toast.makeText(applicationContext, "Object Detected", Toast.LENGTH_SHORT).show()
            }
        }
    }


    override fun onObjectCentered(isCentered: Boolean) {
        // 객체가 중앙에 있는지 여부를 처리하십시오.
        // 예를 들어, 로그를 출력하거나 UI를 업데이트할 수 있습니다.
        Log.d("ObjectCentered", "Is object centered: $isCentered")
    }


    @androidx.camera.core.ExperimentalGetImage
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        // CameraFragment 인스턴스를 가져옵니다.
        cameraFragment = supportFragmentManager.findFragmentById(R.id.camera_fragment) as CameraFragment

        // ObjectDetectorHelper 인스턴스 생성
        objectDetectorHelper = ObjectDetectorHelper(
            context = this,
            objectDetectorListener = this
        )

        // 카메라 프로바이더 인스턴스 가져오기
        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)
        cameraProviderFuture.addListener({
            val cameraProvider = cameraProviderFuture.get()

            // 카메라 선택자 설정
            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

            // 카메라 및 객체 탐지 기능 실행
            objectDetectorHelper.analyzeImage(cameraProvider, cameraSelector, this)
        }, ContextCompat.getMainExecutor(this))
    }

    override fun onError(error: String) {
        // Handle error
    }

    override fun onResults(results: MutableList<Detection>?, inferenceTime: Long, imageHeight: Int, imageWidth: Int) {
        Log.d("MainActivity", "onResults called")
        // 객체가 중앙에서 얼마나 벗어났는지 출력
        results?.firstOrNull()?.let { detection ->
            val boundingBox = detection.boundingBox
            val objectCenterX = (boundingBox.left + boundingBox.right) / 2f
            val objectCenterY = (boundingBox.top + boundingBox.bottom) / 2f

            val screenCenterX = imageWidth.toFloat() / 2f
            val screenCenterY = imageHeight.toFloat() / 2f

            val deltaX = (screenCenterX - objectCenterX) / imageWidth.toFloat()
            val deltaY = (screenCenterY - objectCenterY) / imageHeight.toFloat()

            val deltaXPercentage = (deltaX * 100).toInt()
            val deltaYPercentage = (deltaY * 100).toInt()

            Log.d("Test", "Object center: ($objectCenterX, $objectCenterY)")
            Log.d("Test", "Screen center: ($screenCenterX, $screenCenterY)")
            Log.d("Test", "Delta: ($deltaX, $deltaY)")

            Log.d("Test", "객체가 중앙에서 벗어남: X축: $deltaXPercentage%, Y축: $deltaYPercentage%")
            // 여기에서 onObjectCenterDelta 메소드를 호출하십시오.
            onObjectCenterDelta(deltaX, deltaY)
        }
    }



    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.Q) {
            // Workaround for Android Q memory leak issue in IRequestFinishCallback$Stub.
            // (https://issuetracker.google.com/issues/139738913)
            finishAfterTransition()
        } else {
            super.onBackPressed()
        }
    }
}
