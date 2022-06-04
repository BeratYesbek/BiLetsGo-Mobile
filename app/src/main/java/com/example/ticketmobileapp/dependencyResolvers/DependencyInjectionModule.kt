package com.example.ticketmobileapp.dependencyResolvers

import com.example.ticketmobileapp.services.abstracts.PaymentService
import com.example.ticketmobileapp.services.abstracts.TicketService
import com.example.ticketmobileapp.services.concretes.PaymentManager
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
}