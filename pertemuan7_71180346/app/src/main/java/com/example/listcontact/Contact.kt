package com.example.listcontact

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Contact(
    val imgContact: Int, val nameContact: String, val detailContact: String
    ) : Parcelable
