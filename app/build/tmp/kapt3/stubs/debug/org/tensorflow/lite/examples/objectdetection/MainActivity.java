package org.tensorflow.lite.examples.objectdetection;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\b\u0010\f\u001a\u00020\tH\u0017J\u0012\u0010\r\u001a\u00020\t2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0015J\u0010\u0010\u0010\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0018\u0010\u0013\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0015H\u0016J\u0010\u0010\u0017\u001a\u00020\t2\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J\u0010\u0010\u001a\u001a\u00020\t2\u0006\u0010\u001b\u001a\u00020\u0019H\u0016J0\u0010\u001c\u001a\u00020\t2\u000e\u0010\u001d\u001a\n\u0012\u0004\u0012\u00020\u001f\u0018\u00010\u001e2\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020#H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006%"}, d2 = {"Lorg/tensorflow/lite/examples/objectdetection/MainActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "Lorg/tensorflow/lite/examples/objectdetection/ObjectDetectorHelper$DetectorListener;", "()V", "activityMainBinding", "Lorg/tensorflow/lite/examples/objectdetection/databinding/ActivityMainBinding;", "objectDetectorHelper", "Lorg/tensorflow/lite/examples/objectdetection/ObjectDetectorHelper;", "onAnnotatedImage", "", "annotatedImage", "Landroid/graphics/Bitmap;", "onBackPressed", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onError", "error", "", "onObjectCenterDelta", "deltaX", "", "deltaY", "onObjectCentered", "isCentered", "", "onObjectDetected", "detected", "onResults", "results", "", "Lorg/tensorflow/lite/task/vision/detector/Detection;", "inferenceTime", "", "imageHeight", "", "imageWidth", "app_debug"})
public final class MainActivity extends androidx.appcompat.app.AppCompatActivity implements org.tensorflow.lite.examples.objectdetection.ObjectDetectorHelper.DetectorListener {
    private org.tensorflow.lite.examples.objectdetection.ObjectDetectorHelper objectDetectorHelper;
    private org.tensorflow.lite.examples.objectdetection.databinding.ActivityMainBinding activityMainBinding;
    
    public MainActivity() {
        super();
    }
    
    @java.lang.Override()
    public void onObjectCenterDelta(float deltaX, float deltaY) {
    }
    
    @java.lang.Override()
    public void onAnnotatedImage(@org.jetbrains.annotations.NotNull()
    android.graphics.Bitmap annotatedImage) {
    }
    
    @java.lang.Override()
    public void onObjectDetected(boolean detected) {
    }
    
    @java.lang.Override()
    public void onObjectCentered(boolean isCentered) {
    }
    
    @java.lang.Override()
    @androidx.camera.core.ExperimentalGetImage()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    public void onError(@org.jetbrains.annotations.NotNull()
    java.lang.String error) {
    }
    
    @java.lang.Override()
    public void onResults(@org.jetbrains.annotations.Nullable()
    java.util.List<org.tensorflow.lite.task.vision.detector.Detection> results, long inferenceTime, int imageHeight, int imageWidth) {
    }
    
    @java.lang.Override()
    @java.lang.Deprecated()
    public void onBackPressed() {
    }
}