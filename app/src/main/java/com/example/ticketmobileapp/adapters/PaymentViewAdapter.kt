package com.example.ticketmobileapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.ticketmobileapp.R
import com.example.ticketmobileapp.databinding.CreditCartItemBinding
import com.example.ticketmobileapp.modals.Payment
import com.example.ticketmobileapp.utilities.OnClickListener

class PaymentViewAdapter(private val payments : ArrayList<Payment>,private val onClickListener : OnClickListener<Payment>) : RecyclerView.Adapter<PaymentViewAdapter.PaymentViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val view = DataBindingUtil.inflate<CreditCartItemBinding>(inflater, R.layout.credit_cart_item,parent,false)
        return PaymentViewHolder(view)
    }

    override fun onBindViewHolder(holder: PaymentViewHolder, position: Int) {
        holder.view.payment = payments[position]
        holder.view.cardViewCreditCard.setOnClickListener {
            onClickListener.onClickListener(payments[position])
        }
    }

    override fun getItemCount(): Int {
        return payments.size
    }

    class PaymentViewHolder(var view : CreditCartItemBinding) : RecyclerView.ViewHolder(view.root){

    }
}