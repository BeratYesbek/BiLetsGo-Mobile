package com.example.ticketmobileapp.utilities.results

import com.google.gson.annotations.SerializedName

open class ResponseModel(
    @SerializedName("success")
    val success:Boolean,
    @SerializedName("message")
    val message:String?
){

}