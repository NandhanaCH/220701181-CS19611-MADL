package com.example.nanokids

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.telephony.SmsManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class AdminActivity : AppCompatActivity() {

    private lateinit var messageInput: EditText
    private lateinit var sendEmailBtn: Button
    private lateinit var sendSmsBtn: Button
    private lateinit var registerBtn: Button
    private lateinit var viewBtn: Button
    private lateinit var dbHelper: DBHelper

    private val SMS_PERMISSION_CODE = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)

        messageInput = findViewById(R.id.messageInput)

        sendSmsBtn = findViewById(R.id.sendSmsBtn)
        registerBtn = findViewById(R.id.registerBtn)
        viewBtn = findViewById(R.id.viewBtn)

        dbHelper = DBHelper(this)

        sendEmailBtn.setOnClickListener {
            sendMessageToAll("email")
        }

        sendSmsBtn.setOnClickListener {
            sendMessageToAll("sms")
        }

        registerBtn.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        viewBtn.setOnClickListener {
            startActivity(Intent(this, ViewChildrenActivity::class.java))
        }
    }

    @SuppressLint("QueryPermissionsNeeded")
    private fun sendMessageToAll(type: String) {
        val message = messageInput.text.toString().trim()
        if (message.isEmpty()) {
            Toast.makeText(this, "Enter a message to send", Toast.LENGTH_SHORT).show()
            return
        }

        val contacts = dbHelper.getAllContacts()

        if (contacts.isEmpty()) {
            Toast.makeText(this, "No contacts found in database", Toast.LENGTH_SHORT).show()
            return
        }

        when (type) {
            "email" -> {
                val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
                    data = Uri.parse("mailto:")
                    putExtra(Intent.EXTRA_EMAIL, contacts.map { it.first }.toTypedArray())
                    putExtra(Intent.EXTRA_SUBJECT, "Message from Admin")
                    putExtra(Intent.EXTRA_TEXT, message)
                }
                if (emailIntent.resolveActivity(packageManager) != null) {
                    startActivity(Intent.createChooser(emailIntent, "Send email using:"))
                } else {
                    Toast.makeText(this, "No email app found", Toast.LENGTH_SHORT).show()
                }
            }

            "sms" -> {
                checkSmsPermissionAndSend(message, contacts)
            }
        }
    }

    private fun checkSmsPermissionAndSend(message: String, contacts: List<Pair<String, String>>) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.SEND_SMS),
                SMS_PERMISSION_CODE
            )
        } else {
            sendSmsToAll(message, contacts)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == SMS_PERMISSION_CODE && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            val message = messageInput.text.toString()
            val contacts = dbHelper.getAllContacts()
            sendSmsToAll(message, contacts)
        } else {
            Toast.makeText(this, "SMS permission denied", Toast.LENGTH_SHORT).show()
        }
    }

    private fun sendSmsToAll(message: String, contacts: List<Pair<String, String>>) {
        val smsManager = SmsManager.getDefault()
        for ((_, phone) in contacts) {
            smsManager.sendTextMessage(phone, null, message, null, null)
        }
        Toast.makeText(this, "SMS sent to all contacts", Toast.LENGTH_SHORT).show()
    }
}
