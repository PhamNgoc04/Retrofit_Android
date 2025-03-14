package com.example.learnretrofit.data.repository

import com.example.learnretrofit.data.model.UserResponse
import com.example.learnretrofit.data.network.ApiService

class UserRepository(private val apiService: ApiService) {
    suspend fun getRandomUser(): UserResponse {
        return apiService.getRandomUser()
    }
}
