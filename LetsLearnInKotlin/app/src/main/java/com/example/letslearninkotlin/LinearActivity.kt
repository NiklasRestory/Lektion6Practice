package com.example.letslearninkotlin

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class LinearActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_linear)

        var tv_gotIT : TextView = findViewById(R.id.tv_gotIt)

        val intent = getIntent()
        val anInt = intent.getSerializableExtra("AnInt") as AnInteger
        tv_gotIT.setText(anInt.anInt.toString())
    }
}