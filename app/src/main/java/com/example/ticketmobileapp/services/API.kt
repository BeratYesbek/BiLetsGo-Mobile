package com.example.ticketmobileapp.services

import com.example.ticketmobileapp.modals.abstracts.Entity
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class API {
    companion object {
        val BASE_URL = "https://af32-176-220-172-209.ngrok.io/api/"


        inline fun httpClientInterceptor(): OkHttpClient {
            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor(object : Interceptor {
                override fun intercept(chain: Interceptor.Chain): Response {
                    val request = chain.request()


                    val requestBuild = request.newBuilder()
                        .addHeader("Authorization", "Bearer ")
                        .build()
                    return chain.proceed(requestBuild)
                }

            })
            val client = httpClient.build()
            return client
        }

        // S is a service T is a model
        // for example url = https://localhost:44303/api/products or categories or students
        inline fun <reified S, reified T : Entity> api(): S {
            var baseUrl = "https://af32-176-220-172-209.ngrok.io/api/"

            //When we send request to API must put the model name, but some model name might end with 'y' character.
            // we always use plural name in API that's way if it was ended with 'y' character we need to remove 'y' and we need to put 'ies' end of the model name
            if (T::class.java.simpleName.last() != 'y') {
                baseUrl += "${T::class.java.simpleName}s/"
            } else {
                val length = T::class.java.simpleName.length
                val modelName = T::class.java.simpleName.substring(0, length - 1)
                baseUrl += "${modelName}ies/"
            }

            val client = httpClientInterceptor()
            return Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build()
                .create(S::class.java)
        }

    }
}