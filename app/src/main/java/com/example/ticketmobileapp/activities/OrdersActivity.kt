package com.example.ticketmobileapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ticketmobileapp.R
import com.example.ticketmobileapp.adapters.OrderViewAdapter
import com.example.ticketmobileapp.adapters.TicketViewAdapter
import com.example.ticketmobileapp.auth.CurrentUser
import com.example.ticketmobileapp.databinding.ActivityOrdersBinding
import com.example.ticketmobileapp.modals.dtos.TicketOrderDto
import com.example.ticketmobileapp.mvvm.PurchaseViewModel
import com.example.ticketmobileapp.utilities.OnClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrdersActivity : AppCompatActivity() , OnClickListener<TicketOrderDto>{

    private lateinit var binding : ActivityOrdersBinding
    private lateinit var adapter : OrderViewAdapter
    private val viewModel : PurchaseViewModel by viewModels()
    private val list = ArrayList<TicketOrderDto>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrdersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        runRecyclerView()
        getData()
    }

    private fun getData(){
        viewModel.getByUserId(CurrentUser.user.id!!)
        adapter.notifyDataSetChanged()
        viewModel.orders.observe(this){
            if (it.success){
                list.clear()
                list.addAll(it.data!!)
                adapter.notifyDataSetChanged()
            }
        }
    }

    private fun runRecyclerView() {
        val layoutManager = LinearLayoutManager(this)
        binding.recyclerViewOrders.layoutManager = layoutManager
        adapter = OrderViewAdapter(list,this)
        binding.recyclerViewOrders.adapter = adapter
    }

    override fun onClickListener(data: TicketOrderDto) {
        viewModel.delete(data.purchase)
        viewModel.deleteResult.observe(this){
            if (it){
                Toast.makeText(this,"Your order has been cancelled successfully!!",Toast.LENGTH_LONG).show()
                getData()
            }

        }
    }
}