package com.example.ticketmobileapp.modals

import com.example.ticketmobileapp.modals.abstracts.Entity
import com.google.gson.annotations.SerializedName

class BookedSeat(
    @SerializedName("id")
    val id : Number?,
    @SerializedName("seatId")
    val seatID : Number?,
    @SerializedName("userId")
    val userID : Number?,
    @SerializedName("ticketId")
    val ticketID : Number?,
    @SerializedName("purchaseId")
    val purchaseID : Number?

) : Entity {
}