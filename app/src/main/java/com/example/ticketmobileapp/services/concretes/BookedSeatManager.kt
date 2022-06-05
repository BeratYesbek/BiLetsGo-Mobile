package com.example.ticketmobileapp.services.concretes

import com.example.ticketmobileapp.modals.BookedSeat
import com.example.ticketmobileapp.modals.Purchase
import com.example.ticketmobileapp.services.API
import com.example.ticketmobileapp.services.abstracts.BookedSeatService
import com.example.ticketmobileapp.services.abstracts.PurchaseService
import com.example.ticketmobileapp.utilities.results.SingleResponseModel
import io.reactivex.Single

class BookedSeatManager : BookedSeatService {

    private val api = API.api<BookedSeatService, BookedSeat>()
    override fun add(bookedSeat: BookedSeat): Single<SingleResponseModel<BookedSeat>> {
        return  api.add(bookedSeat)
    }
}