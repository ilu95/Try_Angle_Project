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

    private val thirdWidth = width / 3f
    private val thirdHeight = height / 3f

    // 이 함수가 bounding box가 중심 범위에 완전히 들어가는지 계산하도록 수정됩니다.
    fun calculateDeviationFromCenter(boundingBox: RectF): String {
        val centerX = width / 2f
        val centerY = height / 2f

        val leftLimit = centerX - thirdWidth
        val rightLimit = centerX + thirdWidth
        val topLimit = centerY - thirdHeight
        val bottomLimit = centerY + thirdHeight

        return if (boundingBox.left >= leftLimit && boundingBox.right <= rightLimit &&
            boundingBox.top >= topLimit && boundingBox.bottom <= bottomLimit) {
            "Good" // If object is fully inside the center range, output "Good"
        } else {
            val xDeviationRight = maxOf(boundingBox.right - rightLimit, 0f)
            val xDeviationLeft = maxOf(leftLimit - boundingBox.left, 0f)
            val yDeviationTop = maxOf(topLimit - boundingBox.top, 0f)
            val yDeviationBottom = maxOf(boundingBox.bottom - bottomLimit, 0f)

            "Move ${xDeviationRight}px to the right, ${xDeviationLeft}px to the left, ${yDeviationTop}px up, ${yDeviationBottom}px down"
        }
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

    private fun drawGoldenRatioGrid(canvas: Canvas) {
        val goldenRatioPaint = Paint().apply {
            color = ContextCompat.getColor(context, R.color.golden_ratio_line_color)
            strokeWidth = 2f
        }

        // 황금 비율 상수
        val phi = 0.618034f // Float type

        // 우측 하단
        val rightBottomHorizontal = width * phi
        val rightBottomVertical = height * phi

        // 우측 상단
        val rightTopHorizontal = width * phi
        val rightTopVertical = height * (1 - phi)

        // 좌측 상단
        val leftTopHorizontal = width * (1 - phi)
        val leftTopVertical = height * (1 - phi)

        // 좌측 하단
        val leftBottomHorizontal = width * (1 - phi)
        val leftBottomVertical = height * phi

        // 가로선 그리기
        canvas.drawLine(0f, rightBottomVertical, width.toFloat(), rightBottomVertical, goldenRatioPaint) // 우측 하단
        canvas.drawLine(0f, rightTopVertical, width.toFloat(), rightTopVertical, goldenRatioPaint) // 우측 상단
        canvas.drawLine(0f, leftTopVertical, width.toFloat(), leftTopVertical, goldenRatioPaint) // 좌측 상단
        canvas.drawLine(0f, leftBottomVertical, width.toFloat(), leftBottomVertical, goldenRatioPaint) // 좌측 하단

        // 세로선 그리기
        canvas.drawLine(rightBottomHorizontal, 0f, rightBottomHorizontal, height.toFloat(), goldenRatioPaint) // 우측 하단
        canvas.drawLine(rightTopHorizontal, 0f, rightTopHorizontal, height.toFloat(), goldenRatioPaint) // 우측 상단
        canvas.drawLine(leftTopHorizontal, 0f, leftTopHorizontal, height.toFloat(), goldenRatioPaint) // 좌측 상단
        canvas.drawLine(leftBottomHorizontal, 0f, leftBottomHorizontal, height.toFloat(), goldenRatioPaint) // 좌측 하단
    }


    override fun draw(canvas: Canvas) {
        super.draw(canvas)

        // Draw the golden ratio lines
        drawGoldenRatioGrid(canvas)

        // 3x3 격자 그리기
        drawGrid(canvas)

        for (result in results) {
            val boundingBox = result.boundingBox

            val top = boundingBox.top * scaleFactor
            val bottom = boundingBox.bottom * scaleFactor
            val left = boundingBox.left * scaleFactor
            val right = boundingBox.right * scaleFactor

            val objectCenterX = (boundingBox.left + boundingBox.right) / 2f * scaleFactor
            val objectCenterY = (boundingBox.top + boundingBox.bottom) / 2f * scaleFactor

            // Check if the object is in cell 5 or 8 (central range)
            val cellNumber = getCellNumber(objectCenterX, objectCenterY)
            if (cellNumber == 5 || cellNumber == 8) {
                boxPaint.color = ContextCompat.getColor(context!!, R.color.centered_object_color) // Change color for centered objects
            } else {
                boxPaint.color = ContextCompat.getColor(context!!, R.color.bounding_box_color)
            }

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
            Log.d("Deviation", deviation) // Log the deviation

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
