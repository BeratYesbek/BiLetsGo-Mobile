package com.example.ticketmobileapp.services.abstracts

import com.example.ticketmobileapp.modals.User
import com.example.ticketmobileapp.modals.dtos.AccessToken
import com.example.ticketmobileapp.modals.dtos.UserLoginDto
import com.example.ticketmobileapp.modals.dtos.UserRegisterDto
import com.example.ticketmobileapp.utilities.results.ResponseModel
import com.example.ticketmobileapp.utilities.results.SingleResponseModel
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthService {
    @POST("Auth/login")
    fun login(@Body userLoginDto: UserLoginDto) : Single<SingleResponseModel<AccessToken>>

    @POST("Auth/register")
    fun register(@Body userRegisterDto: UserRegisterDto) : Single<SingleResponseModel<AccessToken>>

    @GET("Auth/isLoggedIn")
    fun isLoggedIn() : Single<SingleResponseModel<User>>

    @GET("Auth/logout")
    fun logout() : Single<ResponseModel>
}