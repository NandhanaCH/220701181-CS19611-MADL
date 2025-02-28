package com.example.digitalcafeloginvalidation

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val email = findViewById<EditText>(R.id.email)
        val pwd = findViewById<EditText>(R.id.pwd)
        val login = findViewById<Button>(R.id.login)

        login.setOnClickListener {
            val email = email.text.toString()
            val pwd = pwd.text.toString()
            if(email.isEmpty() || pwd.isEmpty() )
            {
                Toast.makeText(this,"All fields are Mandatory..!",Toast.LENGTH_LONG).show()
                return@setOnClickListener

            }

            else if(!email.matches(Regex("^[A-Za-z0-9._%+-]+@rajalakshmi.edu.in$")))
            {
                Toast.makeText(this,"Invalid emailid..",Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            else if(!pwd.matches(Regex("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@\$!%*?&]{8,}\$")))
            {
                Toast.makeText(this,"Invalid password...",Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            else
            {
                Toast.makeText(this,"LOGIN SUCCESSFUL...!",Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            }

    }
}
