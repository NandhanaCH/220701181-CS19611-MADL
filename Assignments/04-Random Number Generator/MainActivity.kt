package com.example.randomnumbergenerator

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val min = findViewById<EditText>(R.id.min)
        val max = findViewById<EditText>(R.id.max)
        val gen = findViewById<Button>(R.id.gen)
        val res = findViewById<TextView>(R.id.res)

        gen.setOnClickListener{
            val min = min.text.toString()
            val max = max.text.toString()
            if(min.isNotEmpty() && max.isNotEmpty())
            {
                val min = min.toInt()
                val max=  max.toInt()
                if(min>=max)
                {
                    res.text="Maximum value should be greater than Mininum value"
                }
                else{
                    val r = Random.nextInt(min, max+1)
                    res.text="$r"
                }
            }
            else{
                res.text="Enter Both Values"
            }
        }
    }
}
