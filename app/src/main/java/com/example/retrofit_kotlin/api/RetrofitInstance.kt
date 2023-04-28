package com.example.retrofit_kotlin.api

import com.example.retrofit_kotlin.util.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val requestHeader = OkHttpClient.Builder().apply {
        addInterceptor(MyInterceptor())
    }.build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .client(requestHeader)
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    val api: ApiEndpoint by lazy {
        retrofit.create(ApiEndpoint::class.java)
    }

}