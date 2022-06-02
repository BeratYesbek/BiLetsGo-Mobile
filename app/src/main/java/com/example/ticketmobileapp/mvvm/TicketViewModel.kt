package com.example.ticketmobileapp.mvvm

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.ticketmobileapp.modals.Ticket
import com.example.ticketmobileapp.modals.dtos.TicketReadDto
import com.example.ticketmobileapp.services.abstracts.TicketService
import com.example.ticketmobileapp.utilities.results.ListResponseModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class TicketViewModel @Inject constructor(
    private val ticketService: TicketService,
    application: Application
) : BaseViewModel(application) {

    private val disposable = CompositeDisposable()
    val liveData = MutableLiveData<List<TicketReadDto>>()

    fun getTickets(){
        disposable.add(
            ticketService.getAllTicketDetail()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<ListResponseModel<TicketReadDto>>(){
                    override fun onSuccess(t: ListResponseModel<TicketReadDto>) {
                        if(t.success){
                            liveData.value = t.data!!
                        }
                    }

                    override fun onError(e: Throwable) {

                    }


                })
        )
    }
}