package com.example.nanokids

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val loginButton: Button = findViewById(R.id.loginButton)

        loginButton.setOnClickListener {
            Toast.makeText(this, "Login button clicked", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}
