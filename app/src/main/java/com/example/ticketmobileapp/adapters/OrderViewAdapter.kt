package com.example.ticketmobileapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.ticketmobileapp.R
import com.example.ticketmobileapp.databinding.CreditCartItemBinding
import com.example.ticketmobileapp.databinding.OrderItemBinding
import com.example.ticketmobileapp.modals.Payment
import com.example.ticketmobileapp.modals.dtos.TicketOrderDto
import com.example.ticketmobileapp.modals.dtos.TicketReadDto
import com.example.ticketmobileapp.utilities.OnClickListener

class OrderViewAdapter(private val ticketOrderDto : ArrayList<TicketOrderDto>, private val onClickListener : OnClickListener<TicketOrderDto>) : RecyclerView.Adapter<OrderViewAdapter.OrderViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val view = DataBindingUtil.inflate<OrderItemBinding>(inflater, R.layout.order_item,parent,false)
        return OrderViewHolder(view)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        holder.view.ticketReadDto = ticketOrderDto[position]
        holder.view.btnCancelOrder.setOnClickListener {
            onClickListener.onClickListener(ticketOrderDto[position])
        }
    }

    override fun getItemCount(): Int {
        return ticketOrderDto.size
    }

    class OrderViewHolder(var view : OrderItemBinding) : RecyclerView.ViewHolder(view.root){

    }
}