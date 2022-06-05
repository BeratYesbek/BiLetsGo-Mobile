package com.example.ticketmobileapp.modals

import com.example.ticketmobileapp.modals.abstracts.Entity
import com.google.gson.annotations.SerializedName

class Seat(
    @SerializedName("id")
    val id:Number?,
    @SerializedName("seatNumber")
    val seatNumber : Number?,
    @SerializedName("salonId")
    val salonId : Number?,
    @SerializedName("isBooked")
    val isBooked : Boolean
): Entity{
}