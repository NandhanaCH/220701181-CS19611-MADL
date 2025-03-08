package com.example.alertdialougebox

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val etText : EditText = findViewById(R.id.etText)
        val btDisplay : Button = findViewById(R.id.btDisplay)
        btDisplay.setOnClickListener {
            val alertDialog=AlertDialog.Builder(this)
                .setTitle("MAD LAB")
                .setMessage(etText.text.toString())
                .setPositiveButton("ok"){dialog,which->
                    Toast.makeText(this,"You Clicked Ok",Toast.LENGTH_LONG).show()
                }
                .setNegativeButton("Cancel"){dialog,which->
                    Toast.makeText(this,"You Clicked Cancel",Toast.LENGTH_LONG).show()
                }
                .create()
            alertDialog.show()
        }
    }
}
