package com.example.ticketmobileapp.services.concretes

import com.example.ticketmobileapp.modals.Payment
import com.example.ticketmobileapp.modals.Seat
import com.example.ticketmobileapp.services.API
import com.example.ticketmobileapp.services.abstracts.PaymentService
import com.example.ticketmobileapp.services.abstracts.SeatService
import com.example.ticketmobileapp.utilities.results.ListResponseModel
import io.reactivex.Single

class SeatManager : SeatService {
    private val api = API.api<SeatService, Seat>()

    override fun getBySeatId(salonID: Number?): Single<ListResponseModel<Seat>> {
        return api.getBySeatId(salonID)
    }
}