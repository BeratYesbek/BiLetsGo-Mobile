package com.example.ticketmobileapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.ticketmobileapp.Modals.Ticket
import com.example.ticketmobileapp.R
import com.example.ticketmobileapp.databinding.TicketItemBinding

class TicketViewAdapter(private val tickets : ArrayList<Ticket>) : RecyclerView.Adapter<TicketViewAdapter.TicketViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val view = DataBindingUtil.inflate<TicketItemBinding>(inflater, R.layout.ticket_item,parent,false)
        return TicketViewHolder(view)
    }
    override fun onBindViewHolder(holder: TicketViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return tickets.count()
    }
    class TicketViewHolder(var view : TicketItemBinding): RecyclerView.ViewHolder(view.root)
    {

    }




}