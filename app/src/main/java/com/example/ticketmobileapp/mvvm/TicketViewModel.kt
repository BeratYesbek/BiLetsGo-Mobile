package com.example.ticketmobileapp.mvvm

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.ticketmobileapp.modals.Ticket
import com.example.ticketmobileapp.modals.dtos.TicketReadDto
import com.example.ticketmobileapp.services.abstracts.TicketService
import com.example.ticketmobileapp.utilities.results.ListResponseModel
import com.example.ticketmobileapp.utilities.results.SingleResponseModel
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
    val ticketListLiveData = MutableLiveData<List<TicketReadDto>>()
    val ticketLiveData = MutableLiveData<TicketReadDto>()

    fun getTickets() {
        disposable.add(
            ticketService.getAllTicketDetail()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object :
                    DisposableSingleObserver<ListResponseModel<TicketReadDto>>() {
                    override fun onSuccess(t: ListResponseModel<TicketReadDto>) {
                        if (t.success) {
                            ticketListLiveData.value = t.data!!
                        }
                    }

                    override fun onError(e: Throwable) {

                    }

                })
        )
    }

    fun getTicketById(ticketID: Number?) {
        disposable.add(
            ticketService.getTicketDetailById(ticketID)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object :
                    DisposableSingleObserver<SingleResponseModel<TicketReadDto>>() {
                    override fun onSuccess(t: SingleResponseModel<TicketReadDto>) {
                        if (t.success) {
                            ticketLiveData.value = t.data!!
                        }
                    }

                    override fun onError(e: Throwable) {

                    }

                })
        )
    }
}