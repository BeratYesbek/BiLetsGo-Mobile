package com.example.ticketmobileapp.services.concretes

import com.example.ticketmobileapp.modals.Payment
import com.example.ticketmobileapp.modals.Purchase
import com.example.ticketmobileapp.services.API
import com.example.ticketmobileapp.services.abstracts.PaymentService
import com.example.ticketmobileapp.services.abstracts.PurchaseService
import com.example.ticketmobileapp.utilities.results.ResponseModel
import io.reactivex.Single

class PurchaseManager : PurchaseService {

    private val api = API.api<PurchaseService, Purchase>()

    override fun add(purchase: Purchase) : Single<ResponseModel> {
        return api.add(purchase)
    }
}