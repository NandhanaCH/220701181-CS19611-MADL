package com.example.sendemail


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var emailTo: EditText
    private lateinit var emailSubject: EditText
    private lateinit var emailMessage: EditText
    private lateinit var sendEmailBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        emailTo = findViewById(R.id.emailTo)
        emailSubject = findViewById(R.id.emailSubject)
        emailMessage = findViewById(R.id.emailMessage)
        sendEmailBtn = findViewById(R.id.sendEmailBtn)

        sendEmailBtn.setOnClickListener {
            sendEmail()
        }
    }

    private fun sendEmail() {
        val recipient = emailTo.text.toString().trim()
        val subject = emailSubject.text.toString().trim()
        val message = emailMessage.text.toString().trim()

        if (recipient.isEmpty() || subject.isEmpty() || message.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            return
        }

        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:$recipient")
            putExtra(Intent.EXTRA_SUBJECT, subject)
            putExtra(Intent.EXTRA_TEXT, message)
        }

        try {
            startActivity(Intent.createChooser(intent, "Choose an Email Client"))
        } catch (e: Exception) {
            Toast.makeText(this, "No email client installed", Toast.LENGTH_SHORT).show()
            e.printStackTrace()
        }
    }
}
