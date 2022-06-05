package com.example.ticketmobileapp.utilities.extensions

import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.text.SimpleDateFormat


@BindingAdapter("android:setDate")
fun downloadImage(textView: TextView?, date : String?) {
    textView?.text = date
}