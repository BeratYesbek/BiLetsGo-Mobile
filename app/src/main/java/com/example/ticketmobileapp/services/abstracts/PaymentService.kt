package com.example.ticketmobileapp.services.abstracts

import com.example.ticketmobileapp.modals.Payment
import com.example.ticketmobileapp.utilities.results.ListResponseModel
import com.example.ticketmobileapp.utilities.results.ResponseModel
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PaymentService  {

    @GET("getByUserId/{userId}")
    fun getPaymentMethodsByUserId(@Path("userId") userId: Number) : Single<ListResponseModel<Payment>>

    @POST("add")
    fun add(@Body payment: Payment) : Single<ResponseModel>
}