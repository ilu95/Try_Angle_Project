package org.tensorflow.lite.examples.objectdetection;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 ?2\u00020\u0001:\u0003>?@By\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0005\u0012\b\b\u0002\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0010\b\u0002\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\f\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u000f\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0005\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013\u00a2\u0006\u0002\u0010\u0014J \u0010-\u001a\u00020.2\u0006\u0010/\u001a\u0002002\u0006\u00101\u001a\u0002022\u0006\u00103\u001a\u000204H\u0007J\u0006\u00105\u001a\u00020.J\u0016\u00106\u001a\u00020.2\u0006\u00107\u001a\u0002082\u0006\u00109\u001a\u00020\u0005J,\u0010:\u001a(\u0012\f\u0012\n\u0012\u0004\u0012\u00020\r\u0018\u00010\f\u0012\u0004\u0012\u00020\u000f\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050<0;J\u0006\u0010=\u001a\u00020.R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u001a\u0010\u0007\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u001a\u0010\b\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001a\"\u0004\b\u001e\u0010\u001cR\u000e\u0010\u001f\u001a\u00020 X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u001a\"\u0004\b\"\u0010\u001cR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\u001a\"\u0004\b$\u0010\u001cR\u0010\u0010%\u001a\u0004\u0018\u00010&X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0013\u0010\u0012\u001a\u0004\u0018\u00010\u0013\u00a2\u0006\b\n\u0000\u001a\u0004\b\'\u0010(R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,\u00a8\u0006A"}, d2 = {"Lorg/tensorflow/lite/examples/objectdetection/ObjectDetectorHelper;", "", "threshold", "", "numThreads", "", "maxResults", "currentDelegate", "currentModel", "context", "Landroid/content/Context;", "lastResults", "", "Lorg/tensorflow/lite/task/vision/detector/Detection;", "lastInferenceTime", "", "lastImageHeight", "lastImageWidth", "objectDetectorListener", "Lorg/tensorflow/lite/examples/objectdetection/ObjectDetectorHelper$DetectorListener;", "(FIIIILandroid/content/Context;Ljava/util/List;JIILorg/tensorflow/lite/examples/objectdetection/ObjectDetectorHelper$DetectorListener;)V", "cameraExecutor", "Ljava/util/concurrent/ExecutorService;", "getContext", "()Landroid/content/Context;", "getCurrentDelegate", "()I", "setCurrentDelegate", "(I)V", "getCurrentModel", "setCurrentModel", "imageAnalysis", "Landroidx/camera/core/ImageAnalysis;", "getMaxResults", "setMaxResults", "getNumThreads", "setNumThreads", "objectDetector", "Lorg/tensorflow/lite/task/vision/detector/ObjectDetector;", "getObjectDetectorListener", "()Lorg/tensorflow/lite/examples/objectdetection/ObjectDetectorHelper$DetectorListener;", "getThreshold", "()F", "setThreshold", "(F)V", "analyzeImage", "", "cameraProvider", "Landroidx/camera/lifecycle/ProcessCameraProvider;", "cameraSelector", "Landroidx/camera/core/CameraSelector;", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "clearObjectDetector", "detect", "image", "Landroid/graphics/Bitmap;", "imageRotation", "getResults", "Lkotlin/Triple;", "Lkotlin/Pair;", "setupObjectDetector", "AnnotatedImageAnalyzer", "Companion", "DetectorListener", "app_debug"})
public final class ObjectDetectorHelper {
    private float threshold;
    private int numThreads;
    private int maxResults;
    private int currentDelegate;
    private int currentModel;
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.Nullable()
    private java.util.List<org.tensorflow.lite.task.vision.detector.Detection> lastResults;
    private long lastInferenceTime;
    private int lastImageHeight;
    private int lastImageWidth;
    @org.jetbrains.annotations.Nullable()
    private final org.tensorflow.lite.examples.objectdetection.ObjectDetectorHelper.DetectorListener objectDetectorListener = null;
    @org.jetbrains.annotations.Nullable()
    private org.tensorflow.lite.task.vision.detector.ObjectDetector objectDetector;
    @org.jetbrains.annotations.NotNull()
    private java.util.concurrent.ExecutorService cameraExecutor;
    @org.jetbrains.annotations.NotNull()
    private androidx.camera.core.ImageAnalysis imageAnalysis;
    public static final int DELEGATE_CPU = 0;
    public static final int DELEGATE_GPU = 1;
    public static final int DELEGATE_NNAPI = 2;
    public static final int MODEL_MOBILENETV1 = 0;
    public static final int MODEL_EFFICIENTDETV0 = 1;
    public static final int MODEL_EFFICIENTDETV1 = 2;
    public static final int MODEL_EFFICIENTDETV2 = 3;
    @org.jetbrains.annotations.NotNull()
    public static final org.tensorflow.lite.examples.objectdetection.ObjectDetectorHelper.Companion Companion = null;
    
    public ObjectDetectorHelper(float threshold, int numThreads, int maxResults, int currentDelegate, int currentModel, @org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.Nullable()
    java.util.List<org.tensorflow.lite.task.vision.detector.Detection> lastResults, long lastInferenceTime, int lastImageHeight, int lastImageWidth, @org.jetbrains.annotations.Nullable()
    org.tensorflow.lite.examples.objectdetection.ObjectDetectorHelper.DetectorListener objectDetectorListener) {
        super();
    }
    
    public final float getThreshold() {
        return 0.0F;
    }
    
    public final void setThreshold(float p0) {
    }
    
    public final int getNumThreads() {
        return 0;
    }
    
    public final void setNumThreads(int p0) {
    }
    
    public final int getMaxResults() {
        return 0;
    }
    
    public final void setMaxResults(int p0) {
    }
    
    public final int getCurrentDelegate() {
        return 0;
    }
    
    public final void setCurrentDelegate(int p0) {
    }
    
    public final int getCurrentModel() {
        return 0;
    }
    
    public final void setCurrentModel(int p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.content.Context getContext() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final org.tensorflow.lite.examples.objectdetection.ObjectDetectorHelper.DetectorListener getObjectDetectorListener() {
        return null;
    }
    
    public final void clearObjectDetector() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlin.Triple<java.util.List<org.tensorflow.lite.task.vision.detector.Detection>, java.lang.Long, kotlin.Pair<java.lang.Integer, java.lang.Integer>> getResults() {
        return null;
    }
    
    public final void setupObjectDetector() {
    }
    
    public final void detect(@org.jetbrains.annotations.NotNull()
    android.graphics.Bitmap image, int imageRotation) {
    }
    
    @androidx.camera.core.ExperimentalGetImage()
    public final void analyzeImage(@org.jetbrains.annotations.NotNull()
    androidx.camera.lifecycle.ProcessCameraProvider cameraProvider, @org.jetbrains.annotations.NotNull()
    androidx.camera.core.CameraSelector cameraSelector, @org.jetbrains.annotations.NotNull()
    androidx.lifecycle.LifecycleOwner lifecycleOwner) {
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0017J\f\u0010\t\u001a\u00020\n*\u00020\u000bH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\f"}, d2 = {"Lorg/tensorflow/lite/examples/objectdetection/ObjectDetectorHelper$AnnotatedImageAnalyzer;", "Landroidx/camera/core/ImageAnalysis$Analyzer;", "helper", "Lorg/tensorflow/lite/examples/objectdetection/ObjectDetectorHelper;", "(Lorg/tensorflow/lite/examples/objectdetection/ObjectDetectorHelper;)V", "analyze", "", "imageProxy", "Landroidx/camera/core/ImageProxy;", "imageToBitmap", "Landroid/graphics/Bitmap;", "Landroid/media/Image;", "app_debug"})
    @androidx.camera.core.ExperimentalGetImage()
    public static final class AnnotatedImageAnalyzer implements androidx.camera.core.ImageAnalysis.Analyzer {
        @org.jetbrains.annotations.NotNull()
        private final org.tensorflow.lite.examples.objectdetection.ObjectDetectorHelper helper = null;
        
        public AnnotatedImageAnalyzer(@org.jetbrains.annotations.NotNull()
        org.tensorflow.lite.examples.objectdetection.ObjectDetectorHelper helper) {
            super();
        }
        
        private final android.graphics.Bitmap imageToBitmap(android.media.Image $this$imageToBitmap) {
            return null;
        }
        
        @java.lang.Override()
        @androidx.camera.core.ExperimentalGetImage()
        public void analyze(@org.jetbrains.annotations.NotNull()
        androidx.camera.core.ImageProxy imageProxy) {
        }
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lorg/tensorflow/lite/examples/objectdetection/ObjectDetectorHelper$Companion;", "", "()V", "DELEGATE_CPU", "", "DELEGATE_GPU", "DELEGATE_NNAPI", "MODEL_EFFICIENTDETV0", "MODEL_EFFICIENTDETV1", "MODEL_EFFICIENTDETV2", "MODEL_MOBILENETV1", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0018\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH&J0\u0010\n\u001a\u00020\u00032\u000e\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\f2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011H&\u00a8\u0006\u0013"}, d2 = {"Lorg/tensorflow/lite/examples/objectdetection/ObjectDetectorHelper$DetectorListener;", "", "onError", "", "error", "", "onObjectCenterDelta", "deltaX", "", "deltaY", "onResults", "results", "", "Lorg/tensorflow/lite/task/vision/detector/Detection;", "inferenceTime", "", "imageHeight", "", "imageWidth", "app_debug"})
    public static abstract interface DetectorListener {
        
        public abstract void onError(@org.jetbrains.annotations.NotNull()
        java.lang.String error);
        
        public abstract void onResults(@org.jetbrains.annotations.Nullable()
        java.util.List<org.tensorflow.lite.task.vision.detector.Detection> results, long inferenceTime, int imageHeight, int imageWidth);
        
        public abstract void onObjectCenterDelta(float deltaX, float deltaY);
    }
}