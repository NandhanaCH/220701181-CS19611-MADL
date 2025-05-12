package com.example.sqlite

import android.database.Cursor
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var dbHelper: StudentDBHelper
    private lateinit var rollNoEditText: EditText
    private lateinit var nameEditText: EditText
    private lateinit var cgpaEditText: EditText
    private lateinit var resultTextView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        dbHelper = StudentDBHelper(this)

        rollNoEditText = findViewById(R.id.rollNoEditText)
        nameEditText = findViewById(R.id.nameEditText)
        cgpaEditText = findViewById(R.id.cgpaEditText)
        resultTextView = findViewById(R.id.resultTextView)

        findViewById<Button>(R.id.insertBtn).setOnClickListener { insertStudent() }
        findViewById<Button>(R.id.updateBtn).setOnClickListener { updateStudent() }
        findViewById<Button>(R.id.deleteBtn).setOnClickListener { deleteStudent() }
        findViewById<Button>(R.id.viewBtn).setOnClickListener { viewAllStudents() }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun insertStudent() {
        val roll = rollNoEditText.text.toString()
        val name = nameEditText.text.toString()
        val cgpa = cgpaEditText.text.toString().toDoubleOrNull()

        if (roll.isNotEmpty() && name.isNotEmpty() && cgpa != null) {
            val success = dbHelper.insertStudent(roll, name, cgpa)
            resultTextView.text = if (success) "Inserted Successfully" else "Insertion Failed"
        } else {
            resultTextView.text = "Please fill all fields correctly"
        }
    }

    private fun updateStudent() {
        val roll = rollNoEditText.text.toString()
        val name = nameEditText.text.toString()
        val cgpa = cgpaEditText.text.toString().toDoubleOrNull()

        if (roll.isNotEmpty() && name.isNotEmpty() && cgpa != null) {
            val success = dbHelper.updateStudent(roll, name, cgpa)
            resultTextView.text = if (success) "Updated Successfully" else "Update Failed"
        } else {
            resultTextView.text = "Please fill all fields correctly"
        }
    }

    private fun deleteStudent() {
        val roll = rollNoEditText.text.toString()

        if (roll.isNotEmpty()) {
            val success = dbHelper.deleteStudent(roll)
            resultTextView.text = if (success) "Deleted Successfully" else "Deletion Failed"
        } else {
            resultTextView.text = "Please enter Roll Number"
        }
    }

    private fun viewAllStudents() {
        val cursor: Cursor = dbHelper.getAllStudents()
        val result = StringBuilder()

        if (cursor.moveToFirst()) {
            do {
                val roll = cursor.getString(0)
                val name = cursor.getString(1)
                val cgpa = cursor.getDouble(2)

                result.append("Roll No: $roll\nName: $name\nCGPA: $cgpa\n\n")
            } while (cursor.moveToNext())
        } else {
            result.append("No student records found.")
        }
        resultTextView.text = result.toString()
    }

}
