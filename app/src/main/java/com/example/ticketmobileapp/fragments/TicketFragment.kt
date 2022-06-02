package com.example.ticketmobileapp.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ticketmobileapp.modals.Ticket
import com.example.ticketmobileapp.utilities.OnClickListener
import com.example.ticketmobileapp.activities.TicketDetailActivity
import com.example.ticketmobileapp.adapters.TicketViewAdapter
import com.example.ticketmobileapp.databinding.FragmentTicketBinding

class TicketFragment : Fragment(), OnClickListener<Ticket> {

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
        adapter = TicketViewAdapter(tickets,this)
        dataBinding.recyclerViewTicketFragment.adapter = adapter
    }

    override fun onClickListener(data: Ticket) {
       val intent = Intent(context,TicketDetailActivity::class.java)
        startActivity(intent)
    }


}