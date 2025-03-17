package com.example.learnretrofit.data.network

import com.example.learnretrofit.data.model.LoginRequest
import com.example.learnretrofit.data.model.LoginResponse
import com.example.learnretrofit.data.model.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    // API Đăng nhập
    @POST("api/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    // API Lấy thông tin người dùng
    @GET("api/users/{id}")
    suspend fun getUser(@Path("id") userId: Int): Response<UserResponse>
}
