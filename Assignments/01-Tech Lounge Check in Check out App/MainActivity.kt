package com.example.checkincheckout

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val count : TextView = findViewById(R.id.count)
        val cin : Button = findViewById(R.id.cin)
        val cout : Button = findViewById(R.id.cout)

        var scount =0
        count.text = scount.toString()

        cin.setOnClickListener()
        {
            scount++
            count.text = scount.toString()
        }
        cout.setOnClickListener()
        {
            if(scount>0)
            {
                scount--
            }
            count.text = scount.toString()
        }
    }
}
