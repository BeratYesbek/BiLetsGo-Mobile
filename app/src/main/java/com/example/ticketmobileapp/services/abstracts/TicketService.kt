package com.example.ticketmobileapp.services.abstracts

import com.example.ticketmobileapp.modals.Ticket
import com.example.ticketmobileapp.modals.dtos.TicketReadDto
import com.example.ticketmobileapp.utilities.results.ListResponseModel
import com.example.ticketmobileapp.utilities.results.SingleResponseModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TicketService {
    @GET("getAllDetails")
    fun getAllTicketDetail(): Single<ListResponseModel<TicketReadDto>>

    @GET("getTicketDetailById/{ticketID}")
    fun getTicketDetailById(@Path("ticketID") ticketID : Number?): Single<SingleResponseModel<TicketReadDto>>

}