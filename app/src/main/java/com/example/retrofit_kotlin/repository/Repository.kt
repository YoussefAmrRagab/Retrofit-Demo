package com.example.retrofit_kotlin.repository


import com.example.retrofit_kotlin.api.RetrofitInstance
import com.example.retrofit_kotlin.model.Post
import retrofit2.Response

class Repository {

    suspend fun getPost(): Response<Post> {
        return RetrofitInstance.api.getPost()
    }

    suspend fun getPost(number: Int): Response<Post> {
        return RetrofitInstance.api.getPost(number = number)
    }

    suspend fun getPostsByUserId(id: Int): Response<List<Post>> {
        return RetrofitInstance.api.getPostsByUserId(userId = id)
    }

    suspend fun getPostsByUserId(id: Int, option: Map<String, String>): Response<List<Post>> {
        return RetrofitInstance.api.getPostsByUserId(id, option)
    }

    suspend fun addPost(post: Post): Response<Post> {
        return RetrofitInstance.api.addPost(post)
    }

    suspend fun addPost(auth: String, id: Int, title: String, body: String): Response<Post> {
        return RetrofitInstance.api.addPost(token = auth, userId = id, title = title, body = body)
    }
}