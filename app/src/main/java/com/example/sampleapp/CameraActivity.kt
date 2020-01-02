package com.example.sampleapp

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import io.fotoapparat.Fotoapparat
import io.fotoapparat.log.logcat
import io.fotoapparat.log.loggers
import io.fotoapparat.parameter.ScaleType
import io.fotoapparat.selector.back
import io.fotoapparat.view.CameraView
import kotlinx.android.synthetic.main.activity_camera.*
import java.io.File

class CameraActivity : AppCompatActivity() {

    val permissions = arrayOf(android.Manifest.permission.CAMERA, android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.READ_EXTERNAL_STORAGE)
    var fotoapparat: Fotoapparat? = null

    var fotoapparatState : FotoapparatState? = null
    var cameraStatus : CameraState? = null
    var flashState: FlashState? = null

    val filename= "wunnatest.png"
    val sd= Environment.getExternalStorageDirectory()
    val dest = File(sd,filename)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)

        Log.d("onState ","create")

        createFotoapparat()
        cameraStatus = CameraState.BACK
        flashState = FlashState.OFF
        fotoapparatState = FotoapparatState.OFF


        fab_camera.setOnClickListener(){
            takePhoto()
        }

        fab_flash.setOnClickListener(){
            switchCamera()
        }

        fab_switch.setOnClickListener(){
            changeFlashState()
        }
    }


    fun createFotoapparat(){
        val cameraView =findViewById<CameraView>(R.id.camera)

        fotoapparat = Fotoapparat(
            context = this,
            view = cameraView,
            scaleType = ScaleType.CenterCrop,
            lensPosition = back(),
            logger = loggers(
                logcat()
            ),
            cameraErrorCallback = {error ->
                println("Recorder error : $error")

            }
        )

    }

    override fun onStart() {
        Log.d("onState","start")
        super.onStart()
        if(hasNoPermissions()){
            requestPermission()
        }
        else{
            fotoapparat?.start()
        }
    }

    override fun onResume() {
        super.onResume()
        Log.d("onState ","Resume")

        if (!hasNoPermissions()&& fotoapparatState == FotoapparatState.OFF){
            val intent = Intent(baseContext,CameraActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    override fun onStop() {
        Log.d("onState ","stop")
        super.onStop()
        fotoapparat?.stop()
    }




    fun takePhoto(){

        Log.d("take camera", "Take camera")

        var checkPermission = hasNoPermissions()
        Log.d("has Permission ","$checkPermission")

        if (hasNoPermissions()){


            requestPermission()
        }
        else {
            fotoapparat
                ?.takePicture()
                ?.saveToFile(dest)
        }

    }

    fun switchCamera(){

    }

    fun changeFlashState(){

    }


    private fun hasNoPermissions(): Boolean{
        return ContextCompat.checkSelfPermission(this,
            Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this,
            Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this,
            Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
    }

    fun requestPermission(){
        ActivityCompat.requestPermissions(this, permissions,0)
    }
}

enum class CameraState{
    FRONT, BACK
}

enum class FlashState{
    TORCH, OFF
}

enum class FotoapparatState{
    ON, OFF
}