package com.example.ticketmobileapp.utilities.results

class SingleResponseModel<T>(
    val data: T?,
    success:Boolean,
    message:String?
): ResponseModel(success,message) {
}