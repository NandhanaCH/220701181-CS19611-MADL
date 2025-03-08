package com.example.employeedetails

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.io.File
import java.io.FileWriter
import java.io.IOException

class MainActivity : AppCompatActivity() {
    private val REQUEST_CODE = 1

    private lateinit var etEmployeeId: EditText
    private lateinit var etEmployeeName: EditText
    private lateinit var etEmployeeSalary: EditText
    private lateinit var btnSave: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etEmployeeId = findViewById(R.id.et_employee_id)
        etEmployeeName = findViewById(R.id.et_employee_name)
        etEmployeeSalary = findViewById(R.id.et_employee_salary)
        btnSave = findViewById(R.id.btn_save)

        // Request storage permissions if not granted
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                REQUEST_CODE
            )
        }

        btnSave.setOnClickListener {
            saveToFile()
        }
    }

    private fun saveToFile() {
        val id = etEmployeeId.text.toString()
        val name = etEmployeeName.text.toString()
        val salary = etEmployeeSalary.text.toString()

        if (id.isEmpty() || name.isEmpty() || salary.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            return
        }

        // Correct file storage path for Android 10+
        val folder = File(getExternalFilesDir(null), "EmployeeDetails")
        if (!folder.exists()) {
            folder.mkdirs() // Create folder if not exists
        }

        val file = File(folder, "employees.txt")

        try {
            val writer = FileWriter(file, true)
            writer.append("ID: $id, Name: $name, Salary: $salary\n")
            writer.close()
            Toast.makeText(this, "Saved Successfully! File: ${file.absolutePath}", Toast.LENGTH_LONG).show()
        } catch (e: IOException) {
            e.printStackTrace()
            Toast.makeText(this, "Failed to save!", Toast.LENGTH_SHORT).show()
        }
    }

    // Handle permission request response
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission Granted!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Permission Denied!", Toast.LENGTH_SHORT).show()
            }
        }
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.Q) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 1)
            }
        }

    }

}
