package com.example.ticketmobileapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.ticketmobileapp.R
import com.example.ticketmobileapp.databinding.ActivityTicketDetailBinding
import com.example.ticketmobileapp.mvvm.PaymentViewModel
import com.example.ticketmobileapp.mvvm.TicketViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TicketDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTicketDetailBinding
    private val viewModel: TicketViewModel by viewModels()
    private val paymentViewModel: PaymentViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTicketDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getTickets()
        val ticketID = intent.getStringExtra("ticketID")

        if (ticketID != null) {
            getTicketDetailById(ticketID.toInt())
        }


        binding.btnBuy.setOnClickListener {
            paymentViewModel.getPaymentMethodsByUserID(1)
            paymentViewModel.liveData.observe(this){
                if (it){
                    val intent = Intent(this, PurchaseActivity::class.java)
                    startActivity(intent)
                }else{
                    Toast.makeText(this,"You don't have a payment method. Please add a payment method",Toast.LENGTH_LONG).show()
                    val intent = Intent(this, PaymentActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }

    private fun getTicketDetailById(ticketID: Number) {
        viewModel.getTicketById(ticketID)
        viewModel.ticketLiveData.observe(this) {
            binding.ticketReadDto = it
        }
    }
}