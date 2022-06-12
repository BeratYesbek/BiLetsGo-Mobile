package com.example.ticketmobileapp.services.concretes

import com.example.ticketmobileapp.modals.BookedSeat
import com.example.ticketmobileapp.modals.User
import com.example.ticketmobileapp.modals.abstracts.Entity
import com.example.ticketmobileapp.modals.dtos.AccessToken
import com.example.ticketmobileapp.modals.dtos.UserLoginDto
import com.example.ticketmobileapp.modals.dtos.UserRegisterDto
import com.example.ticketmobileapp.services.API
import com.example.ticketmobileapp.services.abstracts.AuthService
import com.example.ticketmobileapp.services.abstracts.BookedSeatService
import com.example.ticketmobileapp.utilities.results.ResponseModel
import com.example.ticketmobileapp.utilities.results.SingleResponseModel
import io.reactivex.Single

class AuthManager : AuthService {


    private val api = API.api<AuthService, Entity>(false)
    override fun login(userLoginDto: UserLoginDto): Single<SingleResponseModel<AccessToken>> {
        return api.login(userLoginDto)
    }

    override fun register(userRegisterDto: UserRegisterDto): Single<SingleResponseModel<AccessToken>> {
        return api.register(userRegisterDto)
    }

    override fun isLoggedIn(): Single<SingleResponseModel<User>> {
        return api.isLoggedIn()
    }

    override fun logout(): Single<ResponseModel> {
        return api.logout()
    }


}