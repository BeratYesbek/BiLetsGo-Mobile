package com.example.ticketmobileapp.mvvm

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.ticketmobileapp.modals.Seat
import com.example.ticketmobileapp.services.abstracts.SeatService
import com.example.ticketmobileapp.utilities.results.ListResponseModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class SeatViewModel @Inject constructor(
    private val seatService: SeatService,
    application: Application
) : BaseViewModel(application) {

    private val disposable = CompositeDisposable()
    val seatsLiveData = MutableLiveData<List<Seat>>()
    fun getSeatsBySalonID(salonID : Number?){
        disposable.add(
            seatService.getBySeatId(salonID).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<ListResponseModel<Seat>>(){
                    override fun onSuccess(t: ListResponseModel<Seat>) {
                        if (t.success){
                            seatsLiveData.value = t.data!!
                        }
                    }

                    override fun onError(e: Throwable) {

                    }

                })
        )
    }

}