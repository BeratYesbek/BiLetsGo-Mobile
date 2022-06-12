package com.example.ticketmobileapp.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.ticketmobileapp.R
import com.example.ticketmobileapp.activities.LoginActivity
import com.example.ticketmobileapp.activities.OrdersActivity
import com.example.ticketmobileapp.activities.PaymentActivity
import com.example.ticketmobileapp.databinding.FragmentUserBinding
import com.example.ticketmobileapp.mvvm.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserFragment : Fragment() {

    private lateinit var binding : FragmentUserBinding
    private val viewModel : AuthViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserBinding.inflate(layoutInflater)

        binding.btnLogout.setOnClickListener {

            viewModel.logOut()
            viewModel.logoutResult.observe(viewLifecycleOwner){
                if (it){
                    val intent = Intent(context,LoginActivity::class.java)
                    activity?.finish()
                    startActivity(intent)
                }
            }
        }

        binding.btnOrders.setOnClickListener {
            val intent = Intent(context,OrdersActivity::class.java)
            startActivity(intent)
        }
        binding.btnPayment.setOnClickListener {
            val intent = Intent(context,PaymentActivity::class.java)
            startActivity(intent)
        }
        return binding.root.rootView


    }




}