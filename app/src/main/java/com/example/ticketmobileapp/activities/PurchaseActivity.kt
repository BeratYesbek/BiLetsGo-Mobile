package com.example.ticketmobileapp.activities

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.Button
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ticketmobileapp.R
import com.example.ticketmobileapp.adapters.PaymentViewAdapter
import com.example.ticketmobileapp.databinding.ActivityPurchaseBinding
import com.example.ticketmobileapp.modals.Payment
import com.example.ticketmobileapp.mvvm.PaymentViewModel
import com.example.ticketmobileapp.utilities.OnClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PurchaseActivity : AppCompatActivity(),OnClickListener<Payment> {

    private lateinit var binding: ActivityPurchaseBinding
    private lateinit var paymentAdapter : PaymentViewAdapter
    private val payment = ArrayList<Payment>()
    private lateinit var dialog : Dialog
    private val paymentViewModel : PaymentViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPurchaseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.purchaseCardView.setOnClickListener {
            setDialog()
        }
    }

    private fun setDialog() {
        dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.custom_dialog)
        var recyclerView = dialog.findViewById<RecyclerView>(R.id.customRecyclerView)
        var btnClose = dialog.findViewById<Button>(R.id.btnCloseCustomDialog)

        getPaymentMethods()
        runRecyclerView(recyclerView)
        dialog.show()

        btnClose.setOnClickListener {
            dialog.cancel()
        }

    }

    private fun runRecyclerView(recyclerView: RecyclerView){
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        paymentAdapter = PaymentViewAdapter(payment,this)
        recyclerView.adapter = paymentAdapter
    }

    private fun getPaymentMethods(){
        paymentViewModel.getPaymentMethodsByUserID(1)
        paymentViewModel.paymentLiveData.observe(this){
            payment.clear()
            payment.addAll(it)
            paymentAdapter.notifyDataSetChanged()
        }
    }

    override fun onClickListener(data: Payment) {
        dialog.cancel()
    }
}