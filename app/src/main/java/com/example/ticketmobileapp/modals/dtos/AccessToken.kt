package com.example.ticketmobileapp.modals.dtos

import com.example.ticketmobileapp.modals.User
import com.google.gson.annotations.SerializedName

class AccessToken(
    @SerializedName("user")
    val user : User,
    @SerializedName("token")
    val token : String
) {
}