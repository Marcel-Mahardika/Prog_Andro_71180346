package com.example.halaman_login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        //mengambil data dari login form
        val username = intent.getStringExtra("nama")

        val greetings = findViewById<TextView>(R.id.textGreetings)
        greetings.text = "Hai ${username}"
    }
}