package com.example.ticketmobileapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.ticketmobileapp.R
import com.example.ticketmobileapp.databinding.ActivityPaymentBinding

class PaymentActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private lateinit var binding: ActivityPaymentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initializeSpinner()
        binding.spinnerMonth.onItemSelectedListener = this
        binding.spinnerYear.onItemSelectedListener = this

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

    private fun setSelectedYear(year : Any?) {
        print(year)
    }

    private fun setSelectedMonth(month : Any?) {
        print(month)
    }
}