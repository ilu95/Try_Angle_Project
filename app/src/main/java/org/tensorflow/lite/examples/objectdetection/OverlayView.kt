/*
 * Copyright 2022 The TensorFlow Authors. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.tensorflow.lite.examples.objectdetection

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import java.util.LinkedList
import kotlin.math.max
import org.tensorflow.lite.task.vision.detector.Detection
import android.util.Log
import kotlin.math.pow


class OverlayView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private var results: List<Detection> = LinkedList<Detection>()
    private var boxPaint = Paint()
    private var textBackgroundPaint = Paint()
    private var textPaint = Paint()

    private val originalWidth = 640 // 카메라 출력 이미지의 너비
//    private val originalHeight = 480 // 카메라 출력 이미지의 높이

    var scaleFactor: Float = 1f
        get() = width / originalWidth.toFloat()
        private set


    private var bounds = Rect()

    init {
        initPaints()
    }

    fun clear() {
        textPaint.reset()
        textBackgroundPaint.reset()
        boxPaint.reset()
        invalidate()
        initPaints()
    }

    private fun drawGrid(canvas: Canvas) {
        val gridPaint = Paint().apply {
            color = ContextCompat.getColor(context, R.color.grid_line_color)
            strokeWidth = 2f
        }

        // 가로선 그리기
        val horizontalSpacing = height / 3f
        for (i in 1..3) {
            val yPos = i * horizontalSpacing
            canvas.drawLine(0f, yPos, width.toFloat(), yPos, gridPaint)
        }

        // 세로선 그리기
        val verticalSpacing = width / 3f
        for (i in 1..3) {
            val xPos = i * verticalSpacing
            canvas.drawLine(xPos, 0f, xPos, height.toFloat(), gridPaint)
        }
    }

    fun calculateDeviationFromCenter(boundingBox: RectF): Float {
        val centerX = width / 2f
        val centerY = height * 2 / 3f // Update this line to calculate the center of the 2/3 range
        val objectCenterX = (boundingBox.left + boundingBox.right) / 2f
        val objectCenterY = (boundingBox.top + boundingBox.bottom) / 2f

        return kotlin.math.sqrt((centerX - objectCenterX).toDouble().pow(2.0) + (centerY - objectCenterY).toDouble().pow(2.0)).toFloat()
    }

    private fun initPaints() {
        textBackgroundPaint.color = Color.BLACK
        textBackgroundPaint.style = Paint.Style.FILL
        textBackgroundPaint.textSize = 50f

        textPaint.color = Color.WHITE
        textPaint.style = Paint.Style.FILL
        textPaint.textSize = 50f

        boxPaint.color = ContextCompat.getColor(context!!, R.color.bounding_box_color)
        boxPaint.strokeWidth = 8F
        boxPaint.style = Paint.Style.STROKE
    }

    // Change 'private' to 'fun'
    fun getCellNumber(x: Float, y: Float): Int {
        val cellWidth = width / 3f
        val cellHeight = height / 3f
        val cellColumn = (x / cellWidth).toInt()
        val cellRow = (y / cellHeight).toInt()

        return 3 * cellRow + cellColumn + 1
    }


    override fun draw(canvas: Canvas) {
        super.draw(canvas)

        // 3x3 격자 그리기
        drawGrid(canvas)

        for (result in results) {
            val boundingBox = result.boundingBox

            val top = boundingBox.top * scaleFactor
            val bottom = boundingBox.bottom * scaleFactor
            val left = boundingBox.left * scaleFactor
            val right = boundingBox.right * scaleFactor

            // Draw bounding box around detected objects
            val drawableRect = RectF(left, top, right, bottom)
            canvas.drawRect(drawableRect, boxPaint)

            // Create text to display alongside detected objects
            val drawableText =
                result.categories[0].label + " " +
                        String.format("%.2f", result.categories[0].score)

            // Draw rect behind display text
            textBackgroundPaint.getTextBounds(drawableText, 0, drawableText.length, bounds)
            val textWidth = bounds.width()
            val textHeight = bounds.height()
            canvas.drawRect(
                left,
                top,
                left + textWidth + BOUNDING_RECT_TEXT_PADDING,
                top + textHeight + BOUNDING_RECT_TEXT_PADDING,
                textBackgroundPaint
            )

            // Calculate deviation from center
            val deviation = calculateDeviationFromCenter(drawableRect)

            Log.d("Deviation", "Deviation: $deviation")

            // Draw text for detected object
            canvas.drawText(drawableText, left, top + bounds.height(), textPaint)
        }
    }

    fun setResults(
      detectionResults: MutableList<Detection>,
      imageHeight: Int,
      imageWidth: Int,
    ) {
        results = detectionResults

        // PreviewView is in FILL_START mode. So we need to scale up the bounding box to match with
        // the size that the captured images will be displayed.
        scaleFactor = max(width * 1f / imageWidth, height * 1f / imageHeight)
    }

    companion object {
        private const val BOUNDING_RECT_TEXT_PADDING = 8
    }
}
