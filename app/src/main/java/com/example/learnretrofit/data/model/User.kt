package com.example.learnretrofit.data.model

data class User(
    val id: Int,
    val email: String,
    val first_name: String,
    val last_name: String,
    val avatar: String
)

data class Name(val first: String, val last: String)

data class Picture(
    val thumbnail: String, // Đảm bảo có `thumbnail`
    val large: String
)

data class LoginRequest(val email: String, val password: String)

data class LoginResponse(val token: String)

data class UserResponse(val data: User)