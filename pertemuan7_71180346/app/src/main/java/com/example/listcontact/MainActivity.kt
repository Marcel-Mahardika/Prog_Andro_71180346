package com.example.listcontact

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    companion object {
        val INTENT_PARCELABLE = "OBJECT_INTENT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val contactList = listOf<Contact>(
            Contact(
                R.drawable.big_brain,
                "Big Brain",
                "08xxxxxxxxxx"
            ),
            Contact(
                R.drawable.clouck_nice,
                "Clouck Nice",
                "08xxxxxxxxxx"
            ),
            Contact(
                R.drawable.coffin_funeral,
                "Coffin Funeral Parlor",
                "08xxxxxxxxxx"
            ),
            Contact(
                R.drawable.confuse,
                "Confuse Guy",
                "08xxxxxxxxxx"
            ),
            Contact(
                R.drawable.hehehe,
                "Hehehe Guy",
                "08xxxxxxxxxx"
            ),
            Contact(
                R.drawable.im_quit,
                "I'm Quit",
                "08xxxxxxxxxx"
            ),
            Contact(
                R.drawable.impostor,
                "Impostor?",
                "08xxxxxxxxxx"
            ),
            Contact(
                R.drawable.mad_dog,
                "Mad Dog",
                "08xxxxxxxxxx"
            ),
            Contact(
                R.drawable.opportunity,
                "Opportunity Yeah",
                "08xxxxxxxxxx"
            ),
            Contact(
                R.drawable.rapper,
                "Rapper Jedug3x",
                "08xxxxxxxxxx"
            )
        )
        val recyclerView = findViewById<RecyclerView>(R.id.rv_contact)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = ContactAdapter(this, contactList) {
            val intent = Intent(this, DetailContact::class.java)
            intent.putExtra(INTENT_PARCELABLE, it)
            startActivity(intent)
        }
    }
}