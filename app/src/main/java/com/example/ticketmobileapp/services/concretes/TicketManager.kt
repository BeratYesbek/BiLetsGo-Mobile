package com.example.ticketmobileapp.services.concretes

import com.example.ticketmobileapp.modals.Ticket
import com.example.ticketmobileapp.modals.dtos.TicketReadDto
import com.example.ticketmobileapp.services.API
import com.example.ticketmobileapp.services.abstracts.TicketService
import com.example.ticketmobileapp.utilities.results.ListResponseModel
import io.reactivex.Single

class TicketManager : TicketService{

    private val api = API.api<TicketService,Ticket>()
    override fun getAllTicketDetail(): Single<ListResponseModel<TicketReadDto>> {
        return api.getAllTicketDetail()
    }
}