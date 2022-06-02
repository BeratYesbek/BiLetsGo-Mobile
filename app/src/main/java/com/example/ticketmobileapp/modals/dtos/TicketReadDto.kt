package com.example.ticketmobileapp.modals.dtos

import com.example.ticketmobileapp.modals.Category
import com.example.ticketmobileapp.modals.Salon
import com.example.ticketmobileapp.modals.Seat
import com.example.ticketmobileapp.modals.Ticket
import com.google.gson.annotations.SerializedName

class TicketReadDto(
    @SerializedName("Ticket")
    val ticket : Ticket?,
    @SerializedName("Category")
    val category: Category?,
    @SerializedName("Seats")
    val seats : List<Seat>?,
    @SerializedName("Salon")
    val salon : Salon?,
    @SerializedName("Images")
    val images : List<String>?
) {
}