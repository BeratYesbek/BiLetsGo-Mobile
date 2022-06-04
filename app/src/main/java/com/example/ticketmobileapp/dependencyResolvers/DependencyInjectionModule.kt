package com.example.ticketmobileapp.dependencyResolvers

import com.example.ticketmobileapp.services.abstracts.PaymentService
import com.example.ticketmobileapp.services.abstracts.PurchaseService
import com.example.ticketmobileapp.services.abstracts.SeatService
import com.example.ticketmobileapp.services.abstracts.TicketService
import com.example.ticketmobileapp.services.concretes.PaymentManager
import com.example.ticketmobileapp.services.concretes.PurchaseManager
import com.example.ticketmobileapp.services.concretes.SeatManager
import com.example.ticketmobileapp.services.concretes.TicketManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DependencyInjectionModule {

    @Provides
    @Singleton
    fun ticketServiceProvider() : TicketService {
        return TicketManager()
    }

    @Provides
    @Singleton
    fun paymentServiceProvider() : PaymentService {
        return PaymentManager()
    }

    @Provides
    @Singleton
    fun purchaseServiceProvider() : PurchaseService {
        return PurchaseManager()
    }

    @Provides
    @Singleton
    fun seatServiceProvider() : SeatService {
        return SeatManager()
    }
}