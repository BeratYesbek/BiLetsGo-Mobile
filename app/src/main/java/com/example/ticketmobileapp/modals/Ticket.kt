package com.example.ticketmobileapp.modals

import com.example.ticketmobileapp.modals.abstracts.Entity
import com.google.gson.annotations.SerializedName
import java.sql.Date

class Ticket(
    @SerializedName("id")
    val id: Number?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("description")
    val description : String?,
    @SerializedName("categoryId")
    val categoryId : Number?,
    @SerializedName("salonId")
    val salonId : Number?,
    @SerializedName("quantity")
    val quantity : Number?,
    @SerializedName("price")
    val price : Number?,
    @SerializedName("status")
    val status  : Boolean?,
    @SerializedName("created")
    val created  : String?,
    @SerializedName("eventStartedDate")
    val eventStartedDate   : String?,
    @SerializedName("eventFinishedDate")
    val eventFinishedDate   : String?,
    ) : Entity{

}