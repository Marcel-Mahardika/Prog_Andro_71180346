package com.example.tugasfinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_dashboard.*

class ProfileActivity: AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        // menghilangkan actionbar
        getSupportActionBar()?.hide()

        mAuth = FirebaseAuth.getInstance()
        val currentUser = mAuth.currentUser

        val id_txt = findViewById<TextView>(R.id.id_txt)
        val name_txt = findViewById<TextView>(R.id.name_txt)
        val email_txt = findViewById<TextView>(R.id.email_txt)

        id_txt.text = currentUser?.uid
        name_txt.text = currentUser?.displayName
        email_txt.text = currentUser?.email

        Glide.with(this).load(currentUser?.photoUrl).into(profile_image)



        sign_out_btn.setOnClickListener {
            mAuth.signOut()
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}