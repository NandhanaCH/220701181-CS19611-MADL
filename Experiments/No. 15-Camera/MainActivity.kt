package com.example.imagecapture

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    lateinit var imageViewPhoto : ImageView
    private val CAMERA_PERMISSION = 1000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btTakePicture : Button = findViewById(R.id.btTakePicture)
        imageViewPhoto = findViewById(R.id.imageViewPhoto)
        btTakePicture.setOnClickListener {
            val intent= Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent,CAMERA_PERMISSION)
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==CAMERA_PERMISSION && resultCode== RESULT_OK)
        {
            val photo = data!!.extras?.get("data") as Bitmap
            imageViewPhoto.setImageBitmap(photo)
        }
    }

}
