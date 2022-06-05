package com.example.ticketmobileapp.services.abstracts

import com.example.ticketmobileapp.modals.Purchase
import com.example.ticketmobileapp.modals.dtos.TicketOrderDto
import com.example.ticketmobileapp.utilities.results.ListResponseModel
import com.example.ticketmobileapp.utilities.results.ResponseModel
import com.example.ticketmobileapp.utilities.results.SingleResponseModel
import io.reactivex.Single
import retrofit2.http.*

interface PurchaseService {

    @POST("add")
    fun add(@Body purchase : Purchase) : Single<SingleResponseModel<Purchase>>

    @POST("delete")
    fun delete(@Body purchase : Purchase) : Single<ResponseModel>

    @GET("getByUserId/{userID}")
    fun getByUserID(@Path("userID") userID : Number?): Single<ListResponseModel<TicketOrderDto>>

}