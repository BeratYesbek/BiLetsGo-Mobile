package com.example.ticketmobileapp.mvvm

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.ticketmobileapp.modals.Payment
import com.example.ticketmobileapp.services.abstracts.PaymentService
import com.example.ticketmobileapp.utilities.results.ListResponseModel
import com.example.ticketmobileapp.utilities.results.ResponseModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class PaymentViewModel @Inject constructor(
    private val paymentService: PaymentService,
    application: Application
) : BaseViewModel(application) {


    val liveData = MutableLiveData<Boolean>()
    val paymentLiveData = MutableLiveData<List<Payment>>()
    private val disposable = CompositeDisposable()
    fun getPaymentMethodsByUserID(userId : Number){
        disposable.add(
            paymentService.getPaymentMethodsByUserId(userId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<ListResponseModel<Payment>>(){
                    override fun onSuccess(t: ListResponseModel<Payment>) {
                        liveData.value = t.data != null && t.success && t.data.count() > 0
                        paymentLiveData.value = t.data!!
                    }

                    override fun onError(e: Throwable) {
                        liveData.value = false
                    }

                })
        )
    }

    fun add(payment :  Payment){
        disposable.add(
            paymentService.add(payment)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<ResponseModel>(){
                    override fun onSuccess(t: ResponseModel) {
                        liveData.value = t.success
                    }

                    override fun onError(e: Throwable) {
                        liveData.value = false
                    }

                })
        )
    }

}