package com.example.retrofit_kotlin.api

import okhttp3.Interceptor
import okhttp3.Response

class MyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestHeader = chain.request().newBuilder()
            .addHeader("Content-Type", "application/json")
            .addHeader("Authorization", "token")
            .build()
        return chain.proceed(requestHeader)
    }
}