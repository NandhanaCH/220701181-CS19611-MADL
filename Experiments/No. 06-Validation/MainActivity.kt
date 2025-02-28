package com.example.formvalidation

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.reflect.KProperty0

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val user = findViewById<EditText>(R.id.user)
        val pin = findViewById<EditText>(R.id.pin)
        val login = findViewById<Button>(R.id.login)
        val clear = findViewById<Button>(R.id.clear)

        login.setOnClickListener {
            val user = user.text.toString().trim()
            val pin = pin.text.toString().trim()

            if (user.isEmpty() || pin.isEmpty()) {
                Toast.makeText(this, "All values are mandatory...", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            if (!user.matches(Regex("^[a-zA-Z]+$"))) {
                Toast.makeText(this, "Invalid Username...", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            if (!pin.matches(Regex("^[0-9]{4}$"))) {
                Toast.makeText(this, "Invalid Pin Number...", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }
    }

}


