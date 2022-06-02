package com.example.ticketmobileapp.modals

import com.example.ticketmobileapp.modals.abstracts.Entity
import com.google.gson.annotations.SerializedName

class Category(
    @SerializedName("id")
    val id: Number?,
    @SerializedName("name")
    val name: String?
) : Entity {
}