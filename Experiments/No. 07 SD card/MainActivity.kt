package com.example.sdcard

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.FileWriter

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val etroll : EditText = findViewById(R.id.roll)
        val etname : EditText = findViewById(R.id.etname)
        val etcgpa : EditText = findViewById(R.id.cgpa)
        val btsave : Button = findViewById(R.id.btsave)
        val btload : Button = findViewById(R.id.btload)

        btsave.setOnClickListener {
            val rollno = etroll.text.toString()
            val name = etname.text.toString()
            val cgpa = etcgpa.text.toString()
            val file = File(getExternalFilesDir(null ),"student.txt")
            val writer = FileWriter(file)
            writer.write("$rollno\n$name\n$cgpa")
            writer.close()
            Toast.makeText(this , "saved successfully ! ", Toast.LENGTH_LONG).show()
            etroll.text.clear()
            etname.text.clear()
            etcgpa.text.clear()
        }

        btload.setOnClickListener {
            val file = File(getExternalFilesDir(null),"student.txt")
            val reader = BufferedReader(FileReader(file))
            val rollno = reader.readLine()
            val name = reader.readLine()
            val cgpa = reader.readLine()
            reader.close()
            Toast.makeText(this,"load successfully!", Toast.LENGTH_LONG).show()
            etroll.setText(rollno)
            etname.setText(name)
            etcgpa.setText(cgpa)
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
