package com.example.retrofit_kotlin.api

import com.example.retrofit_kotlin.model.Post
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface ApiEndpoint {

    @GET("posts/1")
    suspend fun getPost(): Response<Post>

    @GET("posts/{postNumber}")
    suspend fun getPost(@Path("postNumber") number: Int): Response<Post>

    @GET("posts")
    suspend fun getPostsByUserId(@Query("userId") userId: Int): Response<List<Post>>

    @GET("posts")
    suspend fun getPostsByUserId(
        @Query("userId") userId: Int,
        @QueryMap option: Map<String, String>
    ): Response<List<Post>>

    @POST("posts")
    suspend fun addPost(
        @Body post: Post
    ): Response<Post>

    @Headers("Authorization: bearer token")
    @FormUrlEncoded
    @POST("posts")
    suspend fun addPost(
        @Header("Authorization") token: String,
        @Field("userId") userId: Int,
        @Field("title") title: String,
        @Field("body") body: String
    ): Response<Post>
}