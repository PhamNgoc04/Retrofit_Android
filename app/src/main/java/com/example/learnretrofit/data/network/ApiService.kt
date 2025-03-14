package com.example.learnretrofit.data.network

import com.example.learnretrofit.data.model.UserResponse
import retrofit2.http.GET

interface ApiService {
    @GET("api/")
    suspend fun getRandomUser(): UserResponse
}
