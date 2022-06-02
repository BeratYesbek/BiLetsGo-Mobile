package com.example.ticketmobileapp.modals

import com.example.ticketmobileapp.modals.abstracts.Entity
import com.google.gson.annotations.SerializedName
import java.sql.Date

class Ticket(
    @SerializedName("Id")
    val id: Number?,
    @SerializedName("Title ")
    val title: String?,
    @SerializedName("Description")
    val description : String?,
    @SerializedName("CategoryId")
    val categoryId : Number?,
    @SerializedName("SalonId")
    val salonId : Number?,
    @SerializedName("Quantity")
    val quantity : Number?,
    @SerializedName("Status")
    val status  : Boolean?,
    @SerializedName("Created")
    val created  : Date?,
    @SerializedName("EventStartedDate")
    val EventStartedDate   : Date?,
    @SerializedName("EventFinishedDate")
    val EventFinishedDate   : Date?,
    ) : Entity{

}