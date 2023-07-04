package com.example.myapplication

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide

class AboutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        val tb: Toolbar = findViewById(R.id.AboutActivityTB)
        setSupportActionBar(tb)
        tb.setNavigationOnClickListener{finish()}
        val nameText: TextView = findViewById(R.id.name_text)
        val descriptionText: TextView = findViewById(R.id.descript_text)
        val img: ImageView = findViewById(R.id.img)
        Glide.with(this).load(intent?.extras?.getString(getString(R.string.linkIMGTag))).into(img)
        nameText.text = intent?.extras?.getString(getString(R.string.titleTag))
        descriptionText.text = intent?.extras?.getString(getString(R.string.descriptTag))
    }
}