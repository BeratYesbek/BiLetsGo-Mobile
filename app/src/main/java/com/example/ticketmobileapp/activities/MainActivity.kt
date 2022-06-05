package com.example.ticketmobileapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.testapplication.utilities.CustomSharedPreferences
import com.example.ticketmobileapp.R
import com.example.ticketmobileapp.auth.CurrentUser
import com.example.ticketmobileapp.databinding.ActivityMainBinding
import com.example.ticketmobileapp.mvvm.AuthViewModel
import com.example.ticketmobileapp.utilities.SharedPreferencesToken
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private val viewModel : AuthViewModel by viewModels()
    private lateinit var customSharedPreferences  : CustomSharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        controlSession()
        customSharedPreferences  = CustomSharedPreferences(application)
        SharedPreferencesToken.token = customSharedPreferences.getToken()
        SharedPreferencesToken.userId = customSharedPreferences.getUserId()

        binding.btnLogin.setOnClickListener {
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }

        binding.btnRegister.setOnClickListener {
            val intent = Intent(this,RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun controlSession(){
        viewModel.isLoggedIn()
        viewModel.isLoggedIn.observe(this){
            if (it.success){
                CurrentUser.user = it.data!!

                val intent = Intent(this,BaseActivity::class.java)
                startActivity(intent)
            }else{
                Toast.makeText(this,"You must login! or Register!",Toast.LENGTH_LONG).show()
            }
        }
    }




}