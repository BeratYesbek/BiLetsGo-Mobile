package com.example.ticketmobileapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.Navigation
import com.example.ticketmobileapp.R
import com.example.ticketmobileapp.databinding.ActivityBaseBinding
import com.example.ticketmobileapp.fragments.TicketFragmentDirections
import com.example.ticketmobileapp.fragments.UserFragmentDirections
import com.example.ticketmobileapp.mvvm.TicketViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BaseActivity : AppCompatActivity() {

    private lateinit var dataBinding: ActivityBaseBinding
    private var currentFragment = R.id.home

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = ActivityBaseBinding.inflate(layoutInflater)
        val view = dataBinding.root
        setContentView(view)

        dataBinding.bottomNavigationView.setOnItemSelectedListener { item ->
            if (R.id.home == item.itemId && currentFragment != R.id.home)
                setHomeFragment()
            if (R.id.user == item.itemId && currentFragment != R.id.user)
                setUserFragment()

            true
        }
    }

    private fun setUserFragment() {
        currentFragment = R.id.user
        val action = TicketFragmentDirections.actionTicketFragmentToUserFragment()
        Navigation.findNavController(dataBinding.fragmentContainerView).navigate(action)
    }

    private fun setHomeFragment() {
        currentFragment = R.id.home
        val action = UserFragmentDirections.actionUserFragmentToTicketFragment()
        Navigation.findNavController(dataBinding.fragmentContainerView).navigate(action)
    }


}