package com.example.ticketmobileapp.modals

import com.example.ticketmobileapp.modals.abstracts.Entity
import com.google.gson.annotations.SerializedName

class Seat(
    @SerializedName("Id")
    val id:Number?,
    @SerializedName("SeatNumber")
    val seatNumber : Number?,
    @SerializedName("SalonId")
    val salonId : Number?
): Entity{
}