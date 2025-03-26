package com.example.telephonyservices

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.TelephonyManager
import android.widget.Button
import android.widget.EditText
import androidx.core.app.ActivityCompat

class MainActivity : AppCompatActivity() {
    private val PERMISSION_REQUEST_CODE=101
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val telephonyManager = getSystemService(TELEPHONY_SERVICE) as TelephonyManager

        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.READ_PHONE_STATE
            ) !=
            PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.READ_PHONE_STATE),
                PERMISSION_REQUEST_CODE
            )
        }
        val etNwOpName: EditText = findViewById(R.id.etNwOpName)
        val etCountryISO: EditText = findViewById(R.id.etCountryISO)
        val etPhoneType: EditText = findViewById(R.id.etPhoneType)
        val etSIMState: EditText = findViewById(R.id.etSIMState)
        val etNetworkType: EditText = findViewById(R.id.etNetworkType)

        val btGetTelSer: Button = findViewById(R.id.btGetTelSer)

        etNwOpName.setText(telephonyManager.networkOperatorName)
        etCountryISO.setText(telephonyManager.networkCountryIso)

        val phoneType = when (telephonyManager.phoneType) {
            TelephonyManager.PHONE_TYPE_GSM -> "GSM"
            TelephonyManager.PHONE_TYPE_CDMA -> "CDMA"
            else -> "others"
        }
        etPhoneType.setText(phoneType)
        val simState = when(telephonyManager.simState){
            TelephonyManager.SIM_STATE_READY->"Ready"
            TelephonyManager.SIM_STATE_ABSENT->"Absent"
            else->"others"
        }
        etSIMState.setText(simState)
        val networkType=when(telephonyManager.networkType){
            TelephonyManager.NETWORK_TYPE_LTE->"4G"
            TelephonyManager.NETWORK_TYPE_NR->"5G"
            else->"others"
        }
        etNetworkType.setText(networkType)


    }

    }
