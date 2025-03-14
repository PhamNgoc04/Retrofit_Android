package com.example.learnretrofit.data.model

data class UserResponse(
    val results: List<User>
)

data class User(
    val name: Name,
    val email: String,
    val picture: Picture
)

data class Name(val first: String, val last: String)

data class Picture(
    val thumbnail: String, // Đảm bảo có `thumbnail`
    val large: String
)