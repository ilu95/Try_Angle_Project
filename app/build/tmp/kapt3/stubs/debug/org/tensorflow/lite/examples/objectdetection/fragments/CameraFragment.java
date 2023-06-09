package org.tensorflow.lite.examples.objectdetection.fragments;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000\u00c0\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\t\u0018\u0000 [2\u00020\u00012\u00020\u0002:\u0001[B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0018\u0010%\u001a\u00020&2\u0006\u0010\'\u001a\u00020(2\u0006\u0010)\u001a\u00020(H\u0002J\b\u0010*\u001a\u00020+H\u0003J\u0010\u0010,\u001a\u00020+2\u0006\u0010-\u001a\u00020.H\u0002J\u0018\u0010/\u001a\u00020(2\u0006\u00100\u001a\u00020(2\u0006\u00101\u001a\u00020(H\u0002J\b\u00102\u001a\u00020+H\u0002J\u0010\u00103\u001a\u00020+2\u0006\u00104\u001a\u00020\u0007H\u0016J\u0010\u00105\u001a\u00020+2\u0006\u00106\u001a\u000207H\u0016J$\u00108\u001a\u0002092\u0006\u0010:\u001a\u00020;2\b\u0010<\u001a\u0004\u0018\u00010=2\b\u0010>\u001a\u0004\u0018\u00010?H\u0016J\b\u0010@\u001a\u00020+H\u0016J\u0016\u0010A\u001a\u00020+2\f\u0010B\u001a\b\u0012\u0004\u0012\u00020D0CH\u0002J\u0010\u0010E\u001a\u00020+2\u0006\u0010F\u001a\u00020\u001eH\u0016J\u0018\u0010G\u001a\u00020+2\u0006\u0010H\u001a\u00020I2\u0006\u0010J\u001a\u00020IH\u0016J\u0010\u0010K\u001a\u00020+2\u0006\u0010L\u001a\u00020MH\u0016J\u0010\u0010N\u001a\u00020+2\u0006\u0010O\u001a\u00020MH\u0016J0\u0010P\u001a\u00020+2\u000e\u0010Q\u001a\n\u0012\u0004\u0012\u00020D\u0018\u00010C2\u0006\u0010R\u001a\u00020S2\u0006\u0010T\u001a\u00020(2\u0006\u0010U\u001a\u00020(H\u0016J\b\u0010V\u001a\u00020+H\u0016J\u001a\u0010W\u001a\u00020+2\u0006\u0010X\u001a\u0002092\b\u0010>\u001a\u0004\u0018\u00010?H\u0017J\b\u0010Y\u001a\u00020+H\u0003J\b\u0010Z\u001a\u00020+H\u0002R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\u00020\u00058BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0019\u001a\u00020\u001a8VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u001b\u0010\u001cR\u000e\u0010\u001d\u001a\u00020\u001eX\u0082D\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u001f\u001a\u00020 X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$\u00a8\u0006\\"}, d2 = {"Lorg/tensorflow/lite/examples/objectdetection/fragments/CameraFragment;", "Landroidx/fragment/app/Fragment;", "Lorg/tensorflow/lite/examples/objectdetection/ObjectDetectorHelper$DetectorListener;", "()V", "_fragmentCameraBinding", "Lorg/tensorflow/lite/examples/objectdetection/databinding/FragmentCameraBinding;", "bitmapBuffer", "Landroid/graphics/Bitmap;", "camera", "Landroidx/camera/core/Camera;", "cameraExecutor", "Ljava/util/concurrent/ExecutorService;", "cameraProvider", "Landroidx/camera/lifecycle/ProcessCameraProvider;", "fragmentCameraBinding", "getFragmentCameraBinding", "()Lorg/tensorflow/lite/examples/objectdetection/databinding/FragmentCameraBinding;", "imageAnalyzer", "Landroidx/camera/core/ImageAnalysis;", "objectDetectorHelper", "Lorg/tensorflow/lite/examples/objectdetection/ObjectDetectorHelper;", "overlayView", "Lorg/tensorflow/lite/examples/objectdetection/OverlayView;", "preview", "Landroidx/camera/core/Preview;", "previewView", "", "getPreviewView", "()Ljava/lang/Object;", "tag", "", "viewFinder", "Landroidx/camera/view/PreviewView;", "getViewFinder", "()Landroidx/camera/view/PreviewView;", "setViewFinder", "(Landroidx/camera/view/PreviewView;)V", "aspectRatio", "Landroid/util/Size;", "width", "", "height", "bindCameraUseCases", "", "detectObjects", "image", "Landroidx/camera/core/ImageProxy;", "gcd", "a", "b", "initBottomSheetControls", "onAnnotatedImage", "annotatedImage", "onConfigurationChanged", "newConfig", "Landroid/content/res/Configuration;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "onDetectionResult", "detectionResults", "", "Lorg/tensorflow/lite/task/vision/detector/Detection;", "onError", "error", "onObjectCenterDelta", "deltaX", "", "deltaY", "onObjectCentered", "isCentered", "", "onObjectDetected", "detected", "onResults", "results", "inferenceTime", "", "imageHeight", "imageWidth", "onResume", "onViewCreated", "view", "setUpCamera", "updateControlsUi", "Companion", "app_debug"})
public final class CameraFragment extends androidx.fragment.app.Fragment implements org.tensorflow.lite.examples.objectdetection.ObjectDetectorHelper.DetectorListener {
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String TAG = "CameraFragment";
    private org.tensorflow.lite.examples.objectdetection.OverlayView overlayView;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String tag = "ObjectDetection";
    @org.jetbrains.annotations.Nullable()
    private org.tensorflow.lite.examples.objectdetection.databinding.FragmentCameraBinding _fragmentCameraBinding;
    private org.tensorflow.lite.examples.objectdetection.ObjectDetectorHelper objectDetectorHelper;
    private android.graphics.Bitmap bitmapBuffer;
    @org.jetbrains.annotations.Nullable()
    private androidx.camera.core.Preview preview;
    @org.jetbrains.annotations.Nullable()
    private androidx.camera.core.ImageAnalysis imageAnalyzer;
    @org.jetbrains.annotations.Nullable()
    private androidx.camera.core.Camera camera;
    @org.jetbrains.annotations.Nullable()
    private androidx.camera.lifecycle.ProcessCameraProvider cameraProvider;
    public androidx.camera.view.PreviewView viewFinder;
    
    /**
     * Blocking camera operations are performed using this executor
     */
    private java.util.concurrent.ExecutorService cameraExecutor;
    @org.jetbrains.annotations.NotNull()
    public static final org.tensorflow.lite.examples.objectdetection.fragments.CameraFragment.Companion Companion = null;
    
    public CameraFragment() {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.Object getPreviewView() {
        return null;
    }
    
    private final org.tensorflow.lite.examples.objectdetection.databinding.FragmentCameraBinding getFragmentCameraBinding() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.camera.view.PreviewView getViewFinder() {
        return null;
    }
    
    public final void setViewFinder(@org.jetbrains.annotations.NotNull()
    androidx.camera.view.PreviewView p0) {
    }
    
    private final void onDetectionResult(java.util.List<org.tensorflow.lite.task.vision.detector.Detection> detectionResults) {
    }
    
    @java.lang.Override()
    public void onObjectDetected(boolean detected) {
    }
    
    @java.lang.Override()
    public void onObjectCentered(boolean isCentered) {
    }
    
    @java.lang.Override()
    public void onObjectCenterDelta(float deltaX, float deltaY) {
    }
    
    @java.lang.Override()
    public void onAnnotatedImage(@org.jetbrains.annotations.NotNull()
    android.graphics.Bitmap annotatedImage) {
    }
    
    private final android.util.Size aspectRatio(int width, int height) {
        return null;
    }
    
    private final int gcd(int a, int b) {
        return 0;
    }
    
    @java.lang.Override()
    public void onResume() {
    }
    
    @java.lang.Override()
    public void onDestroyView() {
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull()
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable()
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @java.lang.Override()
    @androidx.camera.core.ExperimentalGetImage()
    @android.annotation.SuppressLint(value = {"MissingPermission"})
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void initBottomSheetControls() {
    }
    
    private final void updateControlsUi() {
    }
    
    @androidx.camera.core.ExperimentalGetImage()
    private final void setUpCamera() {
    }
    
    @android.annotation.SuppressLint(value = {"UnsafeOptInUsageError"})
    private final void bindCameraUseCases() {
    }
    
    private final void detectObjects(androidx.camera.core.ImageProxy image) {
    }
    
    @java.lang.Override()
    public void onConfigurationChanged(@org.jetbrains.annotations.NotNull()
    android.content.res.Configuration newConfig) {
    }
    
    @java.lang.Override()
    public void onResults(@org.jetbrains.annotations.Nullable()
    java.util.List<org.tensorflow.lite.task.vision.detector.Detection> results, long inferenceTime, int imageHeight, int imageWidth) {
    }
    
    @java.lang.Override()
    public void onError(@org.jetbrains.annotations.NotNull()
    java.lang.String error) {
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lorg/tensorflow/lite/examples/objectdetection/fragments/CameraFragment$Companion;", "", "()V", "TAG", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}