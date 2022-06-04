package com.example.ticketmobileapp.modals

import com.example.ticketmobileapp.modals.abstracts.Entity
import com.google.gson.annotations.SerializedName

class Purchase(
    @SerializedName("id")
    val id : Number?,
    @SerializedName("address")
    val address : String?,
    @SerializedName("socialIdentity")
    val socialIdentity : String?,
    @SerializedName("phone")
    val phone : String?,
    @SerializedName("userId")
    val userId : Number?,
    @SerializedName("paymentId")
    val paymentId : Number?,
    @SerializedName("ticketId")
    val ticketId : Number?
) : Entity{
}