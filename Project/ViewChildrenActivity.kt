package com.example.nanokids

import android.os.Bundle
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ViewChildrenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_children)

        val textView: TextView = findViewById(R.id.childrenTextView)
        val childrenDetails = DBHelper(this).getAllChildrenDetails()

        if (childrenDetails.isNotEmpty()) {
            textView.text = childrenDetails.joinToString("\n\n") { child ->
                "ID: ${child["id"]}\n" +
                        "Name: ${child["name"]}\n" +
                        "Age: ${child["age"]}\n" +
                        "Gender: ${child["gender"]}\n" +
                        "Email: ${child["email"]}\n" +
                        "Phone: ${child["phone"]}"
            }
        } else {
            textView.text = "No children registered yet."
        }
    }

}

