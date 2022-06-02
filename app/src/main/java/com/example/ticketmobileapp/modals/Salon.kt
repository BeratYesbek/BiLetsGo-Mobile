package com.example.ticketmobileapp.modals

import com.example.ticketmobileapp.modals.abstracts.Entity
import com.google.gson.annotations.SerializedName

class Salon(
    @SerializedName("Id")
    val id: Number?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("salonNumber")
    val salonNumber: Number?
) : Entity {
}