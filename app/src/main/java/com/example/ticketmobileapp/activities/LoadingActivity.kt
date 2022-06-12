package com.example.ticketmobileapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.testapplication.utilities.CustomSharedPreferences
import com.example.ticketmobileapp.R
import com.example.ticketmobileapp.auth.CurrentUser
import com.example.ticketmobileapp.mvvm.AuthViewModel
import com.example.ticketmobileapp.utilities.SharedPreferencesToken
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoadingActivity : AppCompatActivity() {
    private val viewModel : AuthViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading)
        controlSession()
    }

    private fun controlSession(){
        val customSharedPreferences = CustomSharedPreferences(application)
        val token = customSharedPreferences.getToken()
        val userId = customSharedPreferences.getUserId()
        SharedPreferencesToken.token = token
        SharedPreferencesToken.userId = userId
        viewModel.isLoggedIn()
        viewModel.isLoggedIn.observe(this){
            if (it.success){
                CurrentUser.user = it.data!!

                val intent = Intent(this,BaseActivity::class.java)
                startActivity(intent)
            }else{
               val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}