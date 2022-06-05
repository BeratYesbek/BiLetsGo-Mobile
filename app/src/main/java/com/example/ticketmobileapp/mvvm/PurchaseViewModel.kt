package com.example.ticketmobileapp.mvvm

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.ticketmobileapp.modals.Purchase
import com.example.ticketmobileapp.modals.dtos.TicketOrderDto
import com.example.ticketmobileapp.services.abstracts.PurchaseService
import com.example.ticketmobileapp.utilities.results.ListResponseModel
import com.example.ticketmobileapp.utilities.results.ResponseModel
import com.example.ticketmobileapp.utilities.results.SingleResponseModel
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class PurchaseViewModel @Inject constructor(
    private val purchaseService: PurchaseService,
    application: Application
) :
    BaseViewModel(application) {
    private val disposable = CompositeDisposable()
    val result = MutableLiveData<SingleResponseModel<Purchase>>()
    val orders = MutableLiveData<ListResponseModel<TicketOrderDto>>()
    val deleteResult = MutableLiveData<Boolean>()
    fun add(purchase: Purchase) {
        disposable.add(
            purchaseService.add(purchase)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<SingleResponseModel<Purchase>>() {
                    override fun onSuccess(t: SingleResponseModel<Purchase>) {
                        result.value = t
                    }

                    override fun onError(e: Throwable) {

                    }


                })
        )
    }

    fun getByUserId(userID : Number){
        disposable.add(
            purchaseService.getByUserID(userID)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<ListResponseModel<TicketOrderDto>>() {
                    override fun onSuccess(t: ListResponseModel<TicketOrderDto>) {
                        orders.value = t
                    }

                    override fun onError(e: Throwable) {

                    }


                })
        )
    }

    fun delete(purchase: Purchase){
        disposable.add(
            purchaseService.delete(purchase)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<ResponseModel>() {
                    override fun onSuccess(t: ResponseModel) {
                        deleteResult.value = t.success
                    }

                    override fun onError(e: Throwable) {
                        deleteResult.value = false
                    }


                })
        )
    }
}