package com.example.retrofit_kotlin.model

import com.google.gson.annotations.SerializedName

data class Post(
    @SerializedName("userId")
    val myUserId: Int,
    val id: Int,
    val title: String,
    val body: String
)