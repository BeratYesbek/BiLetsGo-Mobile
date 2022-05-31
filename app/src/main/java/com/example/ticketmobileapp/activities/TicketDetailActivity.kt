package com.example.ticketmobileapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ticketmobileapp.R
import com.example.ticketmobileapp.databinding.ActivityTicketDetailBinding

class TicketDetailActivity : AppCompatActivity()  {
    private lateinit var binding : ActivityTicketDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTicketDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBuy.setOnClickListener {
            val intent = Intent(this,PaymentActivity::class.java)
            startActivity(intent)
        }

    }
}