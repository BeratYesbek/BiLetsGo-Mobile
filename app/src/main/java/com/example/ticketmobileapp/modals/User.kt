package com.example.ticketmobileapp.modals

import com.google.gson.annotations.SerializedName

class User(
    @SerializedName("id")
    val id: Number?,
    @SerializedName("fullName")
    val fullName: String?,
    @SerializedName("email")
    val email: String?
) {
}