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


/**
 * Main entry point into our app. This app follows the single-activity pattern, and all
 * functionality is implemented in the form of fragments.
 */
class MainActivity : AppCompatActivity(), ObjectDetectorHelper.DetectorListener  {
    private lateinit var objectDetectorHelper: ObjectDetectorHelper

    private lateinit var activityMainBinding: ActivityMainBinding

//    override fun onObjectCenterDelta(deltaX: Float, deltaY: Float) {
//        // 여기에서 deltaX, deltaY를 처리하십시오.
//        Log.d("MainActivity", "Delta X: $deltaX, Delta Y: $deltaY")
//        // 이동 거리의 임계값을 설정합니다.
//        val threshold = 0.1f
//
//        // deltaX와 deltaY의 절댓값이 임계값보다 큰지 확인합니다.
//        if (abs(deltaX) > threshold || abs(deltaY) > threshold) {
//            runOnUiThread {
//                Toast.makeText(applicationContext, "객체가 크게 이동했습니다.", Toast.LENGTH_SHORT).show()
//            }
//        }
//        // 예를 들어, 로그를 출력하거나 UI를 업데이트할 수 있습니다.
//        Log.d("ObjectCenterDelta", "Delta X: $deltaX, Delta Y: $deltaY")
//    }
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
            val deltaX = (detection.boundingBox.centerX() - (imageWidth / 2)) / imageWidth.toFloat()
            val deltaY = (detection.boundingBox.centerY() - (imageHeight / 2)) / imageHeight.toFloat()

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
