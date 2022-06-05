package com.example.ticketmobileapp.mvvm

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.testapplication.utilities.CustomSharedPreferences
import com.example.ticketmobileapp.auth.CurrentUser
import com.example.ticketmobileapp.modals.User
import com.example.ticketmobileapp.modals.dtos.AccessToken
import com.example.ticketmobileapp.modals.dtos.UserLoginDto
import com.example.ticketmobileapp.modals.dtos.UserRegisterDto
import com.example.ticketmobileapp.services.abstracts.AuthService
import com.example.ticketmobileapp.utilities.SharedPreferencesToken
import com.example.ticketmobileapp.utilities.results.ResponseModel
import com.example.ticketmobileapp.utilities.results.SingleResponseModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authService: AuthService,
    application: Application
) : BaseViewModel(application) {

    private val disposable = CompositeDisposable()
    val result = MutableLiveData<SingleResponseModel<AccessToken>>()
    val isLoggedIn = MutableLiveData<SingleResponseModel<User>>()
    val logoutResult = MutableLiveData<Boolean>()
    private val customSharedPreferences = CustomSharedPreferences(getApplication())


    fun login(userLoginDto: UserLoginDto){
        disposable.add(
            authService.login(userLoginDto)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<SingleResponseModel<AccessToken>>(){
                    override fun onSuccess(t: SingleResponseModel<AccessToken>) {
                        result.value = t
                        customSharedPreferences.saveToken(t.data!!)
                        SharedPreferencesToken.token = t.data.token
                        SharedPreferencesToken.userId = t.data.user.id                    }

                    override fun onError(e: Throwable) {
                        result.value = SingleResponseModel(null,false,e.message)
                    }

                })

        )
    }
    fun register(userRegisterDto: UserRegisterDto) {
        disposable.add(
            authService.register(userRegisterDto)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<SingleResponseModel<AccessToken>>(){
                    override fun onSuccess(t: SingleResponseModel<AccessToken>) {
                        result.value = t
                        SharedPreferencesToken.token = t.data!!.token
                        SharedPreferencesToken.userId = t.data!!.user.id
                    }

                    override fun onError(e: Throwable) {
                        result.value = SingleResponseModel(null,false,e.message)
                    }

                })

        )
    }

    fun isLoggedIn() {
        disposable.add(
            authService.isLoggedIn()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<SingleResponseModel<User>>(){
                    override fun onSuccess(t: SingleResponseModel<User>) {
                        isLoggedIn.value = t
                    }

                    override fun onError(e: Throwable) {
                        isLoggedIn.value = SingleResponseModel(null,false,e.message)
                    }

                })

        )
    }
    fun logOut() {
        disposable.add(
            authService.logout()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<ResponseModel>(){
                    override fun onSuccess(t: ResponseModel) {
                        logoutResult.value = t.success
                        customSharedPreferences.removeToken()
                        SharedPreferencesToken.token = ""
                        SharedPreferencesToken.userId = null
                    }

                    override fun onError(e: Throwable) {
                        logoutResult.value = false
                    }

                })

        )
    }

}