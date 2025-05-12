package com.example.simplecalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.GridLayout
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var inputText: TextView
    private lateinit var resultText: TextView

    private var currentInput = ""
    private var operator = ""
    private var operand1 = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inputText = findViewById(R.id.inputText)
        resultText = findViewById(R.id.resultText)

        val gridLayout = findViewById<GridLayout>(R.id.gridLayout)
        for (i in 0 until gridLayout.childCount) {
            val button = gridLayout.getChildAt(i) as Button
            button.setOnClickListener { handleButtonClick(button.text.toString()) }
        }
    }

    private fun handleButtonClick(value: String) {
        when (value) {
            "+", "-", "x", "/" -> {
                operand1 = currentInput.toDoubleOrNull() ?: 0.0
                operator = value
                currentInput = ""
                inputText.text = "$operand1 $operator"
                resultText.text = ""
            }

            "=" -> {
                val operand2 = currentInput.toDoubleOrNull() ?: 0.0
                val result = when (operator) {
                    "+" -> operand1 + operand2
                    "-" -> operand1 - operand2
                    "x" -> operand1 * operand2
                    "/" -> if (operand2 != 0.0) operand1 / operand2 else "Error"
                    else -> "Invalid"
                }
                resultText.text = result.toString()
                inputText.text = "$operand1 $operator $operand2"
                currentInput = result.toString()
            }

            "Clear" -> {
                currentInput = ""
                operand1 = 0.0
                operator = ""
                inputText.text = "0"
                resultText.text = ""
            }

            else -> {
                currentInput += value
                resultText.text = currentInput
            }
        }
    }
}
