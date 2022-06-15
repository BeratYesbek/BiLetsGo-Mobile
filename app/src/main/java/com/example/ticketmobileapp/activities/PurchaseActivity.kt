package com.example.ticketmobileapp.activities

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ticketmobileapp.R
import com.example.ticketmobileapp.adapters.PaymentViewAdapter
import com.example.ticketmobileapp.auth.CurrentUser
import com.example.ticketmobileapp.databinding.ActivityPurchaseBinding
import com.example.ticketmobileapp.modals.BookedSeat
import com.example.ticketmobileapp.modals.Payment
import com.example.ticketmobileapp.modals.Purchase
import com.example.ticketmobileapp.mvvm.BookedSeatViewModel
import com.example.ticketmobileapp.mvvm.PaymentViewModel
import com.example.ticketmobileapp.mvvm.PurchaseViewModel
import com.example.ticketmobileapp.utilities.OnClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PurchaseActivity : AppCompatActivity(), OnClickListener<Payment> {

    private lateinit var binding: ActivityPurchaseBinding
    private lateinit var paymentAdapter: PaymentViewAdapter
    private val payment = ArrayList<Payment>()
    private lateinit var dialog: Dialog
    private var paymentID: Number = 0
    private var cardCvv : Number? =0
    private var seatID: Number? = 0
    private var ticketID: Number? = 0
    private val paymentViewModel: PaymentViewModel by viewModels()
    private val purchaseViewModel: PurchaseViewModel by viewModels()
    private val bookedSeatViewModel: BookedSeatViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPurchaseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        seatID = intent.getStringExtra("seatID")?.toInt()
        ticketID = intent.getStringExtra("ticketID")?.toInt()
        var price = intent.getStringExtra("price")
        binding.price.text = "$price$"
        binding.purchaseCardView.setOnClickListener {
            setDialog()
        }

        binding.btnBuy.setOnClickListener {
            setVariables()
        }
    }

    private fun setVariables() {
        val socialIdentity = binding.editTextSocialIdentity.text.toString()
        if (socialIdentity.length != 11) {
            Toast.makeText(this, "Enter 11 character", Toast.LENGTH_LONG).show()
            return
        }
        val address = binding.editTextAddress.text.toString()
        val phoneNumber = binding.editTextPhoneNumber.text.toString()
        if (phoneNumber.length != 10) {
            Toast.makeText(this, "Enter 10 character. Example: 535 555 5555", Toast.LENGTH_LONG)
                .show()
            return
        }
        if (paymentID == 0) {
            Toast.makeText(this, "your must chose a payment method", Toast.LENGTH_LONG).show()
            return
        }
        purchaseViewModel.add(
            Purchase(
                0,
                address,
                socialIdentity,
                phoneNumber,
                CurrentUser.user.id,
                paymentID,
                ticketID
            )
        )

        purchaseViewModel.result.observe(this) {
            if (it.success) {
                bookedSeat(it.data?.id)
                Toast.makeText(
                    this,
                    "your payment has been received successfully",
                    Toast.LENGTH_LONG
                ).show()
                val intent = Intent(this, BaseActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(
                    this,
                    "your payment has not been received! TRY AGAIN LATER",
                    Toast.LENGTH_LONG
                ).show()
                val intent = Intent(this, BaseActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    private fun bookedSeat(purchaseID: Number?) {
        bookedSeatViewModel.add(BookedSeat(0, seatID, CurrentUser.user.id!!, ticketID, purchaseID))
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

    private fun setVerifyCarDialog() {
        val verifyDialog = Dialog(this)
        verifyDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        verifyDialog.setCancelable(false)
        verifyDialog.setContentView(R.layout.custom_verify_card_dialog_item)
        var editTextCvv = verifyDialog.findViewById<EditText>(R.id.editTextCvvPaymentVerify)
        var btnVerify = verifyDialog.findViewById<Button>(R.id.btnCvvPaymentVerify)
        var btnClose = verifyDialog.findViewById<Button>(R.id.btnClosePaymentVerify)
        btnVerify.setOnClickListener {
            var cvv = editTextCvv.text.toString()
            if (!cvv.isNullOrBlank()) {
                if (cvv.toInt() == cardCvv?.toInt()){
                    verifyDialog.cancel()
                    Toast.makeText(this,"Cvv has been verified successfully",Toast.LENGTH_LONG).show()

                }else{
                    Toast.makeText(this,"Cvv has not been verified",Toast.LENGTH_LONG).show()
                }
            }
        }
        btnClose.setOnClickListener {
            verifyDialog.cancel()
        }
        verifyDialog.show()
    }

    private fun runRecyclerView(recyclerView: RecyclerView) {
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        paymentAdapter = PaymentViewAdapter(payment, this)
        recyclerView.adapter = paymentAdapter
    }

    private fun getPaymentMethods() {
        paymentViewModel.getPaymentMethodsByUserID(CurrentUser.user.id!!)
        paymentViewModel.paymentLiveData.observe(this) {
            payment.clear()
            payment.addAll(it)
            paymentAdapter.notifyDataSetChanged()
        }
    }

    override fun onClickListener(data: Payment) {
        paymentID = data.id
        cardCvv = data.cvv
        dialog.cancel()
        setVerifyCarDialog()
    }

}