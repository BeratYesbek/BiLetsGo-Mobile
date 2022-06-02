package com.example.ticketmobileapp.utilities.results

class ListResponseModel<T>(
    val data: List<T>?,
    success:Boolean,
    message:String?
) :ResponseModel(success,message) {
}