package com.example.ticketmobileapp.modals

import com.example.ticketmobileapp.modals.abstracts.Entity
import com.google.gson.annotations.SerializedName

class Category(
    @SerializedName("Id")
    val id: Number?,
    @SerializedName("Name")
    val name: String?
) : Entity {
}