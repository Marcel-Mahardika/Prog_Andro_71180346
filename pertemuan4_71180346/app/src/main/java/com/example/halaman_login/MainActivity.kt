package com.example.halaman_login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //menghubungkan MainActivity Login dengan MainAcitivy Daftar
        val button = findViewById<Button>(R.id.button3)
            button.setOnClickListener {
                val intent = Intent(this, MainActivity2::class.java)
                startActivity(intent)
        }
    }
}