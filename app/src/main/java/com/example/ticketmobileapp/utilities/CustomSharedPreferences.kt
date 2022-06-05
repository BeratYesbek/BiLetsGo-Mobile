package com.example.testapplication.utilities

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.core.content.edit
import com.example.ticketmobileapp.modals.dtos.AccessToken

class CustomSharedPreferences {

    companion object {

        private var sharedPreferences: SharedPreferences? = null

        @Volatile
        private var instance: CustomSharedPreferences? = null
        private val lock = Any()

        operator fun invoke(context: Context): CustomSharedPreferences =
            instance ?: synchronized(lock) {
                instance ?: makeSharedPreferences(context).also {
                    instance = it
                }
            }

        private fun makeSharedPreferences(context: Context): CustomSharedPreferences {
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
            return CustomSharedPreferences()
        }
    }

    fun saveToken(accessToken: AccessToken) {
        sharedPreferences?.edit(commit = true) {
            putString("token", accessToken.token)
            putInt("userId", accessToken.user.id!!.toInt())
        }
    }

    fun removeToken() = sharedPreferences?.edit(commit = true) {
        remove("token")
        remove("userId")
    }

    fun getToken() = sharedPreferences?.getString("token", "")
    fun getUserId() = sharedPreferences?.getInt("userId", 0)
}