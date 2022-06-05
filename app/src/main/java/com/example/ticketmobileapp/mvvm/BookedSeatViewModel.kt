package com.example.ticketmobileapp.mvvm

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.ticketmobileapp.modals.BookedSeat
import com.example.ticketmobileapp.services.abstracts.BookedSeatService
import com.example.ticketmobileapp.utilities.results.SingleResponseModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class BookedSeatViewModel @Inject constructor(
    private val bookedSeatService: BookedSeatService,
    application: Application
) : BaseViewModel(application) {

    private val disposable = CompositeDisposable()
    val result = MutableLiveData<Boolean>()
    fun add(bookedSeat: BookedSeat){
        disposable.add(
            bookedSeatService.add(bookedSeat)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<SingleResponseModel<BookedSeat>>(){
                    override fun onSuccess(t: SingleResponseModel<BookedSeat>) {
                        result.value = t.success
                    }

                    override fun onError(e: Throwable) {
                    }

                })
        )
    }
}