package com.example.ticketmobileapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ticketmobileapp.Modals.Ticket
import com.example.ticketmobileapp.adapters.TicketViewAdapter
import com.example.ticketmobileapp.databinding.FragmentTicketBinding

class TicketFragment : Fragment() {

    private lateinit var dataBinding: FragmentTicketBinding
    private lateinit var adapter : TicketViewAdapter
    private lateinit var tickets : ArrayList<Ticket>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = FragmentTicketBinding.inflate(inflater)

        tickets = ArrayList<Ticket>()
        tickets.add(Ticket())
        tickets.add(Ticket())
        tickets.add(Ticket())
        tickets.add(Ticket())
        tickets.add(Ticket())
        tickets.add(Ticket())
        tickets.add(Ticket())
        tickets.add(Ticket())


        runRecyclerView()

        return dataBinding.root
    }

    private fun runRecyclerView() {
        val layoutManager = LinearLayoutManager(context)
        dataBinding.recyclerViewTicketFragment.layoutManager = layoutManager
        adapter = TicketViewAdapter(tickets)
        dataBinding.recyclerViewTicketFragment.adapter = adapter
    }


}