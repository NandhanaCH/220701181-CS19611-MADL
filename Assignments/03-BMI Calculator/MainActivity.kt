package com.example.bmicalculator

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
        val wt = findViewById<EditText>(R.id.wt)
        val ht = findViewById<EditText>(R.id.ht)
        val bmi = findViewById<Button>(R.id.bmi)
        val res = findViewById<TextView>(R.id.result)

        bmi.setOnClickListener {
            val wt = wt.text.toString()
            val ht = ht.text.toString()

            if (wt.isNotEmpty() && ht.isNotEmpty()) {
                val wt = wt.toDouble()
                val ht = ht.toDouble()

                if(ht>0) {
                    val bmi = wt / (ht * ht)
                    val cat = when {
                        bmi < 18.5 -> "Underweight"
                        bmi in 18.5..24.9 -> "Normal Weight"
                        bmi in 25.0..29.9 -> "Overweight"
                        else -> "Obese"
                    }
                    res.text = "BMI : %.2f\nCategory : %s".format(bmi, cat)

                }
                else {
                    res.text = "Height should be greater than 0"
                }

            } else {
                res.text = "Enter Both Values"
            }
        }
        }
    }
