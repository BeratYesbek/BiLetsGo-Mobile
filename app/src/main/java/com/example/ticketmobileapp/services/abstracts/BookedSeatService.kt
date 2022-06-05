package com.example.ticketmobileapp.services.abstracts

import com.example.ticketmobileapp.modals.BookedSeat
import com.example.ticketmobileapp.utilities.results.SingleResponseModel
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface BookedSeatService {

    @POST("add")
    fun add(@Body bookedSeat : BookedSeat) : Single<SingleResponseModel<BookedSeat>>
}