package com.example.ticketmobileapp.fragments

import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ticketmobileapp.modals.Ticket
import com.example.ticketmobileapp.utilities.OnClickListener
import com.example.ticketmobileapp.activities.TicketDetailActivity
import com.example.ticketmobileapp.adapters.TicketViewAdapter
import com.example.ticketmobileapp.databinding.FragmentTicketBinding
import com.example.ticketmobileapp.modals.dtos.TicketReadDto
import com.example.ticketmobileapp.mvvm.TicketViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TicketFragment : Fragment(), OnClickListener<TicketReadDto> {
    private val viewModel : TicketViewModel by viewModels()
    private lateinit var dataBinding: FragmentTicketBinding
    private lateinit var adapter : TicketViewAdapter
    private lateinit var tickets : ArrayList<TicketReadDto>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = FragmentTicketBinding.inflate(inflater)

        tickets = ArrayList<TicketReadDto>()
        dataBinding.progressBar.visibility = View.VISIBLE

        viewModel.getTickets()
        viewModel.ticketListLiveData.observe(viewLifecycleOwner) {
            dataBinding.progressBar.visibility = View.INVISIBLE
            tickets.clear()
            tickets.addAll(it)
            adapter.notifyDataSetChanged()
        }

        runRecyclerView()

        return dataBinding.root
    }
    private fun runRecyclerView() {
        val layoutManager = LinearLayoutManager(context)
        dataBinding.recyclerViewTicketFragment.layoutManager = layoutManager
        adapter = TicketViewAdapter(tickets,this)
        dataBinding.recyclerViewTicketFragment.adapter = adapter
    }

    override fun onClickListener(data: TicketReadDto) {
       val intent = Intent(context,TicketDetailActivity::class.java)
        val ticketID = data.ticket!!.id
        intent.putExtra("ticketID",ticketID.toString())
        intent.putExtra("hello","Hello")
        startActivity(intent)
    }


}