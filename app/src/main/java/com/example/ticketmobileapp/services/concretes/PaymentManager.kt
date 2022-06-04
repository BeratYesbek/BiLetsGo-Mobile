package com.example.ticketmobileapp.services.concretes

import com.example.ticketmobileapp.modals.Payment
import com.example.ticketmobileapp.modals.Ticket
import com.example.ticketmobileapp.services.API
import com.example.ticketmobileapp.services.abstracts.PaymentService
import com.example.ticketmobileapp.services.abstracts.TicketService
import com.example.ticketmobileapp.utilities.results.ListResponseModel
import com.example.ticketmobileapp.utilities.results.ResponseModel
import io.reactivex.Single

class PaymentManager : PaymentService {


    private val api = API.api<PaymentService, Payment>()
    override fun getPaymentMethodsByUserId(userID: Number): Single<ListResponseModel<Payment>> {
        return api.getPaymentMethodsByUserId(userID)
    }

    override fun add(payment: Payment): Single<ResponseModel> {
        return api.add(payment)
    }


}