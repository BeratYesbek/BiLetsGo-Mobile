package com.example.ticketmobileapp.mvvm

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.ticketmobileapp.modals.Purchase
import com.example.ticketmobileapp.services.abstracts.PurchaseService
import com.example.ticketmobileapp.utilities.results.ResponseModel
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
    val result = MutableLiveData<Boolean>()
    fun add(purchase: Purchase) {
        disposable.add(
            purchaseService.add(purchase)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<ResponseModel>() {
                    override fun onSuccess(t: ResponseModel) {
                        result.value = t.success
                    }

                    override fun onError(e: Throwable) {
                        result.value = false
                    }

                })
        )
    }
}