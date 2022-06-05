package com.example.ticketmobileapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.ticketmobileapp.R
import com.example.ticketmobileapp.auth.CurrentUser
import com.example.ticketmobileapp.databinding.ActivityRegisterBinding
import com.example.ticketmobileapp.modals.dtos.UserRegisterDto
import com.example.ticketmobileapp.mvvm.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterActivity : AppCompatActivity() {

    private lateinit var binding : ActivityRegisterBinding
    private val authViewModel : AuthViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegister.setOnClickListener {
            register()
        }
    }

    private fun register(){
        val email = binding.editTextRegisterEmail.text.toString()
        val fullName = binding.editTextFullName.text.toString()
        val password = binding.editTextRegisterPassword.text.toString()

        authViewModel.register(UserRegisterDto(fullName,email,password))

        authViewModel.result.observe(this){
            if (it.success){
                CurrentUser.user = it.data!!.user
                val intent = Intent(this,BaseActivity::class.java)
                startActivity(intent)
            }else{
                Toast.makeText(this,it.message, Toast.LENGTH_LONG).show()

            }
        }
    }
}