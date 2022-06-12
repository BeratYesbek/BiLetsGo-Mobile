package com.example.ticketmobileapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.ticketmobileapp.R
import com.example.ticketmobileapp.auth.CurrentUser
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
            paymentViewModel.getPaymentMethodsByUserID(CurrentUser.user.id!!)
            paymentViewModel.liveData.observe(this){
                if (it){
                    val intent = Intent(this, SeatSelectionActivity::class.java)
                    val salonID = binding.ticketReadDto?.ticket?.salonId?.toString()
                    val ticketID = binding.ticketReadDto?.ticket?.id.toString()
                    val price = binding.ticketReadDto?.ticket?.price?.toString()

                    intent.putExtra("salonID",salonID)
                    intent.putExtra("ticketID",ticketID)
                    intent.putExtra("price",price)
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