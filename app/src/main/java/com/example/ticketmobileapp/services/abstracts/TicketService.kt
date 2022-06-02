package com.example.ticketmobileapp.services.abstracts

import com.example.ticketmobileapp.modals.dtos.TicketReadDto
import com.example.ticketmobileapp.utilities.results.ListResponseModel
import io.reactivex.Single
import retrofit2.http.GET

interface TicketService {
    @GET("")
   fun getAllTicketDetail(): Single<ListResponseModel<TicketReadDto>>
}