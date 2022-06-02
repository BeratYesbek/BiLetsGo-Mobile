package com.example.ticketmobileapp.services.abstracts

import com.example.ticketmobileapp.modals.Ticket
import com.example.ticketmobileapp.modals.dtos.TicketReadDto
import com.example.ticketmobileapp.utilities.results.ListResponseModel
import io.reactivex.Single
import retrofit2.http.GET

interface TicketService {
    @GET("getAllDetails")
   fun getAllTicketDetail(): Single<ListResponseModel<TicketReadDto>>
}