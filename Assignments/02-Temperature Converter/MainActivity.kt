package com.example.temperatureconverter

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val temp = findViewById<EditText>(R.id.input)
        val c = findViewById<Button>(R.id.celsius)
        val f = findViewById<Button>(R.id.fahrenheit)
        val res = findViewById<TextView>(R.id.result)

        c.setOnClickListener {
            val input = temp.text.toString()
            if (input.isNotEmpty()) {
                val cel = input.toDouble()
                val far = (cel * 9 / 5) + 32
                res.text = "$cel C = %.2f F".format(far)
            } else {
                res.text = "Enter Value"
            }
        }

        f.setOnClickListener {
            val input = temp.text.toString()
            if (input.isNotEmpty()) {
                val far = input.toDouble()
                val cel = (far * 9 / 5) + 32
                res.text = "$far F = %.2f C".format(cel)
            } else {
                res.text = "Enter Value"
            }
        }
    }
    }
