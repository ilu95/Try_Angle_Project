package org.tensorflow.lite.examples.objectdetection;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\b\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010!\n\u0002\b\u0004\u0018\u0000 -2\u00020\u0001:\u0001-B\u0019\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0002\u0010\u0006J\u000e\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cJ\u0006\u0010\u001d\u001a\u00020\u001eJ\u0010\u0010\u001f\u001a\u00020\u001e2\u0006\u0010 \u001a\u00020!H\u0016J\u0010\u0010\"\u001a\u00020\u001e2\u0006\u0010 \u001a\u00020!H\u0002J\u0010\u0010#\u001a\u00020\u001e2\u0006\u0010 \u001a\u00020!H\u0002J\u0016\u0010$\u001a\u00020\f2\u0006\u0010%\u001a\u00020\u00112\u0006\u0010&\u001a\u00020\u0011J\b\u0010\'\u001a\u00020\u001eH\u0002J$\u0010(\u001a\u00020\u001e2\f\u0010)\u001a\b\u0012\u0004\u0012\u00020\u000f0*2\u0006\u0010+\u001a\u00020\f2\u0006\u0010,\u001a\u00020\fR\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082D\u00a2\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R \u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u00118F@BX\u0086\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0015\u001a\u00020\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006."}, d2 = {"Lorg/tensorflow/lite/examples/objectdetection/OverlayView;", "Landroid/view/View;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "bounds", "Landroid/graphics/Rect;", "boxPaint", "Landroid/graphics/Paint;", "originalWidth", "", "results", "", "Lorg/tensorflow/lite/task/vision/detector/Detection;", "<set-?>", "", "scaleFactor", "getScaleFactor", "()F", "textBackgroundPaint", "textPaint", "thirdHeight", "thirdWidth", "calculateDeviationFromCenter", "", "boundingBox", "Landroid/graphics/RectF;", "clear", "", "draw", "canvas", "Landroid/graphics/Canvas;", "drawGoldenRatioGrid", "drawGrid", "getCellNumber", "x", "y", "initPaints", "setResults", "detectionResults", "", "imageHeight", "imageWidth", "Companion", "app_debug"})
public final class OverlayView extends android.view.View {
    @org.jetbrains.annotations.NotNull()
    private java.util.List<? extends org.tensorflow.lite.task.vision.detector.Detection> results;
    @org.jetbrains.annotations.NotNull()
    private android.graphics.Paint boxPaint;
    @org.jetbrains.annotations.NotNull()
    private android.graphics.Paint textBackgroundPaint;
    @org.jetbrains.annotations.NotNull()
    private android.graphics.Paint textPaint;
    private final int originalWidth = 640;
    private float scaleFactor = 1.0F;
    @org.jetbrains.annotations.NotNull()
    private android.graphics.Rect bounds;
    private final float thirdWidth = 0.0F;
    private final float thirdHeight = 0.0F;
    private static final int BOUNDING_RECT_TEXT_PADDING = 8;
    @org.jetbrains.annotations.NotNull()
    public static final org.tensorflow.lite.examples.objectdetection.OverlayView.Companion Companion = null;
    
    public OverlayView(@org.jetbrains.annotations.Nullable()
    android.content.Context context, @org.jetbrains.annotations.Nullable()
    android.util.AttributeSet attrs) {
        super(null);
    }
    
    public final float getScaleFactor() {
        return 0.0F;
    }
    
    public final void clear() {
    }
    
    private final void drawGrid(android.graphics.Canvas canvas) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String calculateDeviationFromCenter(@org.jetbrains.annotations.NotNull()
    android.graphics.RectF boundingBox) {
        return null;
    }
    
    private final void initPaints() {
    }
    
    public final int getCellNumber(float x, float y) {
        return 0;
    }
    
    private final void drawGoldenRatioGrid(android.graphics.Canvas canvas) {
    }
    
    @java.lang.Override()
    public void draw(@org.jetbrains.annotations.NotNull()
    android.graphics.Canvas canvas) {
    }
    
    public final void setResults(@org.jetbrains.annotations.NotNull()
    java.util.List<org.tensorflow.lite.task.vision.detector.Detection> detectionResults, int imageHeight, int imageWidth) {
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lorg/tensorflow/lite/examples/objectdetection/OverlayView$Companion;", "", "()V", "BOUNDING_RECT_TEXT_PADDING", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}