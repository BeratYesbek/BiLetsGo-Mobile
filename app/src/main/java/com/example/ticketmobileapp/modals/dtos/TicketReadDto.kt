package com.example.ticketmobileapp.modals.dtos

import com.example.ticketmobileapp.modals.Category
import com.example.ticketmobileapp.modals.Salon
import com.example.ticketmobileapp.modals.Seat
import com.example.ticketmobileapp.modals.Ticket
import com.google.gson.annotations.SerializedName

class TicketReadDto(
    @SerializedName("ticket")
    val ticket : Ticket?,
    @SerializedName("category")
    val category: Category?,
    @SerializedName("seats")
    val seats : List<Seat>?,
    @SerializedName("salon")
    val salon : Salon?,
    @SerializedName("images")
    val images : List<String>?
) {
}