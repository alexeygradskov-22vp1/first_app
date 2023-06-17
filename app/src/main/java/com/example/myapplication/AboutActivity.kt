package com.example.myapplication

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class AboutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        val tb: Toolbar = findViewById(R.id.AboutActivityTB)
        setSupportActionBar(tb)
        tb.setNavigationOnClickListener{finish()}
        val position = intent?.extras?.getInt("position")?:(0)
        val nameText: TextView = findViewById(R.id.name_text)
        val descriptionText: TextView = findViewById(R.id.descript_text)
        tb.title = resources.getStringArray(R.array.items1)[position]
        nameText.text = resources.getStringArray(R.array.items1)[position]
        descriptionText.text = resources.getStringArray(R.array.items2)[position]
    }
}