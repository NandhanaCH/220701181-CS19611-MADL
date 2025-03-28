package com.example.sendsms

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.telephony.SmsManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    private lateinit var phoneNumber: EditText
    private lateinit var message: EditText
    private lateinit var sendBtn: Button

    companion object {
        private const val SMS_PERMISSION_CODE = 101
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        phoneNumber = findViewById(R.id.phoneNumber)
        message = findViewById(R.id.message)
        sendBtn = findViewById(R.id.sendBtn)

        // Check and request SMS permission
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.SEND_SMS), SMS_PERMISSION_CODE)
        }

        sendBtn.setOnClickListener {
            sendSMS()
        }
    }

    private fun sendSMS() {
        val number = phoneNumber.text.toString().trim()
        val msg = message.text.toString().trim()

        if (number.isEmpty() || msg.isEmpty()) {
            Toast.makeText(this, "Please enter phone number and message", Toast.LENGTH_SHORT).show()
            return
        }

        try {
            val smsManager: SmsManager = SmsManager.getDefault()
            smsManager.sendTextMessage(number, null, msg, null, null)
            Toast.makeText(this, "Message Sent", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Toast.makeText(this, "Failed to send message", Toast.LENGTH_SHORT).show()
            e.printStackTrace()
        }
    }

    // Handle permission result
    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == SMS_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission Granted!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Permission Denied!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
