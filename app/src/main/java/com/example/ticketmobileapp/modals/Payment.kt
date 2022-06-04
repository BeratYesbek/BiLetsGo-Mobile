package com.example.ticketmobileapp.modals

import com.example.ticketmobileapp.modals.abstracts.Entity
import com.google.gson.annotations.SerializedName

class Payment(
    @SerializedName("id")
    val id : Number,
    @SerializedName("cardNumber")
    val cardNumber:String,
    @SerializedName("nameOnTheCard")
    val NameOnTheCard:String,
    @SerializedName("cardName")
    val cardName:String,
    @SerializedName("year")
    val year : Number,
    @SerializedName("month")
    val month : Number,
    @SerializedName("cvv")
    val cvv : Number,
    @SerializedName("userId")
    val userId : Number
) : Entity {
}