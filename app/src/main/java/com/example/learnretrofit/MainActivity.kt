package com.example.learnretrofit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.learnretrofit.data.network.ApiService
import com.example.learnretrofit.data.repository.UserRepository
import com.example.learnretrofit.ui.appNavGraph
import com.example.learnretrofit.ui.viewmodel.UserViewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Khởi tạo Retrofit
        val apiService = Retrofit.Builder()
            .baseUrl("https://randomuser.me/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)

        // Tạo Repository
        val repository = UserRepository(apiService)

        // Khởi tạo ViewModel (Không dùng Hilt)
        val userViewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
                return UserViewModel(repository) as T
            }
        })[UserViewModel::class.java]

        setContent {
            val navController = rememberNavController()

            // Sử dụng NavHost
            NavHost(navController = navController, startDestination = "user") {
                appNavGraph(navController, userViewModel)
            }
        }
    }
}
