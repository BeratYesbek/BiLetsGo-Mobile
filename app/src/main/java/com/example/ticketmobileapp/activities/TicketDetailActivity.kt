package com.example.ticketmobileapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.ticketmobileapp.R
import com.example.ticketmobileapp.databinding.ActivityTicketDetailBinding
import com.example.ticketmobileapp.mvvm.TicketViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TicketDetailActivity : AppCompatActivity()  {
    private lateinit var binding : ActivityTicketDetailBinding
    private val viewModel : TicketViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTicketDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getTickets()

        binding.btnBuy.setOnClickListener {
            val intent = Intent(this,PaymentActivity::class.java)
            startActivity(intent)
        }

    }
}