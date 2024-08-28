package com.example.randomnumbergenerator

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONArray
import java.io.File

class MainActivity : AppCompatActivity() {

    private lateinit var tvNumbers: TextView
    private lateinit var btnGenerate: Button
    private val fileName = "random_numbers.json"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvNumbers = findViewById(R.id.tvNumbers)
        btnGenerate = findViewById(R.id.btnGenerate)

        btnGenerate.setOnClickListener {
            generateRandomNumbers()
        }

        displaySavedNumbers()
    }

    private fun generateRandomNumbers() {
        val randomNumbers = List(10) { (1..100).random() }

        saveNumbersToJson(randomNumbers)

        tvNumbers.text = getString(R.string.numbers_placeholder) + " \n" + randomNumbers.joinToString(", ")
    }

    private fun saveNumbersToJson(numbers: List<Int>) {
        val jsonArray = JSONArray(numbers)
        val file = File(filesDir, fileName)

        file.writeText(jsonArray.toString())
    }

    private fun displaySavedNumbers() {
        val file = File(filesDir, fileName)
        if (file.exists()) {
            val json = file.readText()
            val jsonArray = JSONArray(json)
            val savedNumbers = List(jsonArray.length()) { jsonArray.getInt(it) }

            tvNumbers.text = getString(R.string.numbers_placeholder) + " \n" + savedNumbers.joinToString(", ")
        }
    }
}
