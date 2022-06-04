package com.example.ticketmobileapp.adapters.gridViewAdapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import com.example.ticketmobileapp.R
import com.example.ticketmobileapp.modals.Seat
import com.example.ticketmobileapp.utilities.OnClickListener

class SeatGridViewAdapter(val seats : List<Seat>,val context : Context,val onClickListener: OnClickListener<Seat>) : BaseAdapter() {
    override fun getCount(): Int {
        return  seats.count()
    }

    override fun getItem(p0: Int): Any {
        return p0
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val view : View = View.inflate(context, R.layout.custom_seat_selection_item,null)
        val button = view.findViewById<Button>(R.id.btnSeat)
        button.text = seats[p0]?.seatNumber?.toString()
        button.setOnClickListener {
        onClickListener.onClickListener(seats[p0])
        }

        return view
    }

}