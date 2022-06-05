package com.example.ticketmobileapp.dependencyResolvers

import com.example.ticketmobileapp.services.abstracts.*
import com.example.ticketmobileapp.services.concretes.*
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

    @Provides
    @Singleton
    fun bookedSeatServiceProvider() : BookedSeatService {
        return BookedSeatManager()
    }

    @Provides
    @Singleton
    fun authServiceProvider() : AuthService {
        return AuthManager()
    }
}