package com.example.bookdetails

import android.os.Bundle
import android.os.Environment
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.File
import java.io.FileWriter
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var etTitle: EditText
    private lateinit var etAuthor: EditText
    private lateinit var etPublisher: EditText
    private lateinit var etPrice: EditText
    private lateinit var btnSave: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        etTitle = findViewById(R.id.et_title)
        etAuthor = findViewById(R.id.et_author)
        etPublisher = findViewById(R.id.et_publisher)
        etPrice = findViewById(R.id.et_price)
        btnSave = findViewById(R.id.btn_save)

        btnSave.setOnClickListener {
            saveBookDetails()
        }
    }

    private fun saveBookDetails() {
        val title = etTitle.text.toString().trim()
        val author = etAuthor.text.toString().trim()
        val publisher = etPublisher.text.toString().trim()
        val price = etPrice.text.toString().trim()

        if (title.isEmpty() || author.isEmpty() || publisher.isEmpty() || price.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            return
        }

        // Save to App-Specific External Storage (Scoped Storage)
        val folder = File(getExternalFilesDir(null), "BookDetails")
        if (!folder.exists()) {
            folder.mkdirs()
        }

        val file = File(folder, "books.txt")

        try {
            val writer = FileWriter(file, true)
            writer.append("Title: $title, Author: $author, Publisher: $publisher, Price: $price\n")
            writer.close()
            Toast.makeText(this, "Book details saved successfully!\nPath: ${file.absolutePath}", Toast.LENGTH_LONG).show()
        } catch (e: IOException) {
            e.printStackTrace()
            Toast.makeText(this, "Failed to save book details!", Toast.LENGTH_SHORT).show()
        }
    }
}
