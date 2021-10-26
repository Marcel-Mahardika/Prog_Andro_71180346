package com.example.listcontact

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class ContactAdapter(private val context: Context, private val contact: List<Contact>, val listener: (Contact) -> Unit)
    : RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    class ContactViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val imgContact = view.findViewById<ImageView>(R.id.img_item_photo)
        val nameContact = view.findViewById<TextView>(R.id.item_name)
        val detailContact = view.findViewById<TextView>(R.id.item_description)

        fun bindView(contact: Contact, listener: (Contact) -> Unit) {
            imgContact.setImageResource(contact.imgContact)
            nameContact.text = contact.nameContact
            detailContact.text = contact.detailContact
            itemView.setOnClickListener {
                listener(contact)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        return ContactViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_contact, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.bindView(contact[position], listener)
    }

    override fun getItemCount(): Int = contact.size
}