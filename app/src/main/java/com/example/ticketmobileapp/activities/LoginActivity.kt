package com.example.ticketmobileapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.ticketmobileapp.R
import com.example.ticketmobileapp.auth.CurrentUser
import com.example.ticketmobileapp.databinding.ActivityLoginBinding
import com.example.ticketmobileapp.modals.User
import com.example.ticketmobileapp.modals.dtos.UserLoginDto
import com.example.ticketmobileapp.mvvm.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val authViewModel : AuthViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            login()
        }
    }

    private fun login(){
        val email = binding.editTextLoginEmail.text.toString()
        if (!email.contains("@") && !email.contains(".com"))
        {
            Toast.makeText(this,"This is not an email address",Toast.LENGTH_LONG).show()
            return
        }
        val password = binding.editTextLoginPassword.text.toString()
        if (password.length < 6){
            Toast.makeText(this,"Password must be six character",Toast.LENGTH_LONG).show()
            return
        }
        authViewModel.login(UserLoginDto(email,password))
        authViewModel.result.observe(this){
            if(it.success){
                CurrentUser.user = it.data!!.user
                val intent = Intent(this,BaseActivity::class.java)
                startActivity(intent)
            }else{
                Toast.makeText(this,it.message,Toast.LENGTH_LONG).show()
            }
        }
    }



}