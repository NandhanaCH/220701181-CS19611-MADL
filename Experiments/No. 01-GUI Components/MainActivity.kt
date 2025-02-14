package com.example.guicomponents

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import com.example.guicomponents.R

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val tvText : TextView = findViewById(R.id.tvText)
        val btFontSize : Button = findViewById(R.id.btFontSize)
        val btFontColor : Button = findViewById(R.id.btFontColor)
        val btBackgroundColor : Button = findViewById(R.id.btBackgroundColor)

        var fontSize = 5.0f
        btFontSize.setOnClickListener{
            tvText.textSize = fontSize
            fontSize = (fontSize + 5) % 50
        }

        var fontColor = 0
        btFontColor.setOnClickListener {
            when (fontColor % 3) {
                0 -> tvText.setTextColor(Color.RED)
                1 -> tvText.setTextColor(Color.GREEN)
                2 -> tvText.setTextColor(Color.BLUE)
            }
            fontColor++
        }

        var bgColor = 0
        val linearlayout = findViewById<LinearLayout>(R.id.main)

        btBackgroundColor.setOnClickListener {
            when (bgColor % 3) {
                0 -> linearlayout.setBackgroundColor(Color.RED)
                1 -> linearlayout.setBackgroundColor(Color.GREEN)
                2 -> linearlayout.setBackgroundColor(Color.BLUE)
            }
            bgColor++
        }
    }
}
