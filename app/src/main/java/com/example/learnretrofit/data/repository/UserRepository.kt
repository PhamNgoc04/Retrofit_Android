package com.example.learnretrofit.data.repository

import com.example.learnretrofit.data.model.LoginRequest
import com.example.learnretrofit.data.model.LoginResponse
import com.example.learnretrofit.data.model.UserResponse
import com.example.learnretrofit.data.network.ApiService
import retrofit2.Response

class UserRepository(private val apiService: ApiService) {
    suspend fun login(email: String, password: String): Response<LoginResponse> {
        return apiService.login(LoginRequest(email, password))
    }

    suspend fun getUser(userId: Int): Response<UserResponse> {
        return apiService.getUser(userId)
    }
}
