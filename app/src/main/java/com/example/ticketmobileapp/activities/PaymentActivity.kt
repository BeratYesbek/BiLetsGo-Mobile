package com.example.ticketmobileapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import com.example.ticketmobileapp.R
import com.example.ticketmobileapp.auth.CurrentUser
import com.example.ticketmobileapp.databinding.ActivityPaymentBinding
import com.example.ticketmobileapp.modals.Payment
import com.example.ticketmobileapp.mvvm.PaymentViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.sql.Date
import java.sql.Time
import java.sql.Timestamp
@AndroidEntryPoint
class PaymentActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private lateinit var binding: ActivityPaymentBinding
    private var year = 2022
    private var month = 6
    private var ticketID : Number ?= 0
    private var salonID : Number ?= 0
    private var price : Number ?= 0

    private val viewModel : PaymentViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initializeSpinner()
        binding.spinnerMonth.onItemSelectedListener = this
        binding.spinnerYear.onItemSelectedListener = this

        salonID  = intent.getStringExtra("salonID")?.toInt()
        ticketID = intent.getStringExtra("ticketID")?.toInt()
        price = intent.getStringExtra("price")?.toInt()

        binding.btnSaveCard.setOnClickListener {
            validateValues()
        }
    }

    private fun validateValues() {
        val cardNumber = binding.editTextCardNumber.text.toString()


        val nameOnTheCard = binding.editTextNameOnTheCard.text.toString()
        val cvv = binding.editTextCvv.text.toString()
        val cardName = binding.editTextCardName.text.toString()
        if(cardNumber.length != 16){
            Toast.makeText(this,"Enter 16 character",Toast.LENGTH_LONG).show()
            return
        }
        if(nameOnTheCard.isNullOrBlank()){
            Toast.makeText(this,"Name on the card cannot be blank",Toast.LENGTH_LONG).show()
            return
        }
        if(cvv.isNullOrBlank()){
            Toast.makeText(this,"Cvv cannot be blank",Toast.LENGTH_LONG).show()
            return
        }
        val payment = Payment(0, cardNumber, nameOnTheCard, cardName, year, month, cvv.toInt(),
            CurrentUser.user.id!!
        )
        addPayment(payment)
    }

    private fun addPayment(payment : Payment) {
        viewModel.add(payment)
        viewModel.liveData.observe(this){
            if (it && ticketID != null){
                val intent = Intent(this, SeatSelectionActivity::class.java)
                intent.putExtra("salonID",salonID)
                intent.putExtra("ticketID",ticketID)
                intent.putExtra("price",price)
                startActivity(intent)
                finish()
                return@observe
            }
            if (it  && (ticketID == null || ticketID == 0)){
                Toast.makeText(this,"Payment method has been added. Successfully",Toast.LENGTH_LONG).show()
                finish()
            }else{
                Toast.makeText(this,"Payment method has not been added.",Toast.LENGTH_LONG).show()
            }
        }
    }


    private fun initializeSpinner() {
        val yearAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.years,
            android.R.layout.simple_spinner_dropdown_item
        )
        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerYear.adapter = yearAdapter;

        val monthAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.months,
            android.R.layout.simple_spinner_dropdown_item
        )
        monthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerMonth.adapter = monthAdapter;
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        val selected = p0?.getItemAtPosition(0)
        when (p0?.id) {
            R.id.spinnerYear -> setSelectedYear(selected)
            R.id.spinnerMonth -> setSelectedMonth(selected)
        }
        print(selected);
    }


    override fun onNothingSelected(p0: AdapterView<*>?) {

    }

    private fun setSelectedYear(year: Any?) {
        this.year = year.toString().toInt()
    }

    private fun setSelectedMonth(month: Any?) {
        this.month = month.toString().toInt()
    }
}