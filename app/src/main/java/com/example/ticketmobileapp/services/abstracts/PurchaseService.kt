package com.example.ticketmobileapp.services.abstracts

import com.example.ticketmobileapp.modals.Purchase
import com.example.ticketmobileapp.utilities.results.ResponseModel
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface PurchaseService {

    @POST("add")
    fun add(@Body purchase : Purchase) : Single<ResponseModel>
}