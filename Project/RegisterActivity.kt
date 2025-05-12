package com.example.nanokids

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.nanokids.R

class RegisterActivity : AppCompatActivity() {

    private lateinit var childName: EditText
    private lateinit var childEmail: EditText
    private lateinit var childPhone: EditText
    private lateinit var childAge: EditText
    private lateinit var genderGroup: RadioGroup
    private lateinit var registerChildButton: Button
    private lateinit var dbHelper: DBHelper

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Initialize views
        childName = findViewById(R.id.childName)
        childEmail = findViewById(R.id.parentEmail)
        childPhone = findViewById(R.id.parentPhone)
        childAge = findViewById(R.id.age)
        genderGroup = findViewById(R.id.genderGroup)
        registerChildButton = findViewById(R.id.saveBtn)

        // Initialize DBHelper
        dbHelper = DBHelper(this)

        // Register button click listener
        registerChildButton.setOnClickListener {
            registerChild()
        }
    }

    private fun registerChild() {
        // Get values from form
        val name = childName.text.toString().trim()
        val email = childEmail.text.toString().trim()
        val phone = childPhone.text.toString().trim()
        val age = childAge.text.toString().trim()
        val genderId = genderGroup.checkedRadioButtonId
        val gender = findViewById<RadioButton>(genderId)?.text.toString()

        // Check if any field is empty
        if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || age.isEmpty() || gender.isEmpty()) {
            Toast.makeText(this, "Please fill in all the fields", Toast.LENGTH_SHORT).show()
        } else {
            // Add the child to the database
            dbHelper.addChild(name, email, phone, age.toInt(), gender)

            // Show a success message
            Toast.makeText(this, "Child registered successfully", Toast.LENGTH_SHORT).show()

            // Clear the form
            clearForm()
        }
    }

    private fun clearForm() {
        childName.text.clear()
        childEmail.text.clear()
        childPhone.text.clear()
        childAge.text.clear()
        genderGroup.clearCheck()
    }
}
