package com.example.ticketmobileapp.services.abstracts

import com.example.ticketmobileapp.modals.Seat
import com.example.ticketmobileapp.utilities.results.ListResponseModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface SeatService {
    @GET("getBySalonId/{salonID}")
    fun getBySeatId(@Path("salonID") salonID : Number?) : Single<ListResponseModel<Seat>>
}