package com.example.myapplication

import android.app.ActionBar
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.ComponentActivity

class AboutActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        val backBtn : ImageView = findViewById(R.id.back_button_tb)
        backBtn.setOnClickListener{
            finish()
        }
        val position = intent?.extras?.getInt("position")?.or(0)
        val nameText: TextView = findViewById(R.id.name_text)
        val descriptionText: TextView = findViewById(R.id.descript_text)
        nameText.setText(resources.getStringArray(R.array.items1)[position!!])
        descriptionText.setText(resources.getStringArray(R.array.items2)[position])
    }
}