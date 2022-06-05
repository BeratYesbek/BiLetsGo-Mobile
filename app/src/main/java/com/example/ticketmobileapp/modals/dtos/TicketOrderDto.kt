package com.example.ticketmobileapp.modals.dtos

import com.example.ticketmobileapp.modals.*
import com.google.gson.annotations.SerializedName

class TicketOrderDto(
    @SerializedName("ticket")
    val ticket : Ticket?,
    @SerializedName("category")
    val category: Category?,
    @SerializedName("seats")
    val seats : List<Seat>?,
    @SerializedName("salon")
    val salon : Salon?,
    @SerializedName("images")
    val images : List<String>?,
    @SerializedName("purchase")
    val purchase : Purchase,
    @SerializedName("bookedSeat")
    val bookedSeat: BookedSeat
) {
}