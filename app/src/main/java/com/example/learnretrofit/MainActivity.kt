package com.example.learnretrofit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.learnretrofit.data.model.User
import com.example.learnretrofit.data.network.ApiService
import com.example.learnretrofit.data.repository.UserRepository
import com.example.learnretrofit.ui.screens.HistoryScreen
import com.example.learnretrofit.ui.screens.LoginScreen
import com.example.learnretrofit.ui.screens.UserDetailScreen
import com.example.learnretrofit.ui.screens.UserScreen
import com.example.learnretrofit.ui.viewmodel.UserViewModel
import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val apiService = Retrofit.Builder()
            .baseUrl("https://reqres.in/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)

        val repository = UserRepository(apiService)

        val userViewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
                return UserViewModel(repository) as T
            }
        })[UserViewModel::class.java]

        setContent {
            val navController = rememberNavController()
            val userViewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return UserViewModel(repository) as T
                }
            })[UserViewModel::class.java]

            NavHost(navController = navController, startDestination = "login") {
                composable("login") {
                    LoginScreen(userViewModel, navController)
                }
                composable("user/{userId}") { backStackEntry ->
                    val userId = backStackEntry.arguments?.getString("userId")?.toIntOrNull() ?: 1
                    UserScreen(userViewModel, navController, userId)
                }
                composable("history") {
                    HistoryScreen(navController, userViewModel)
                }
                composable("user_detail/{userJson}") { backStackEntry ->
                    val userJson = backStackEntry.arguments?.getString("userJson")
                    val user = Gson().fromJson(userJson, User::class.java)
                    UserDetailScreen(user = user, navController = navController)
                }

            }
        }


    }
}


    /*
    {
  "email": "eve.holt@reqres.in",
  "password": "cityslicka"
}

     */

