package com.example.learnretrofit.ui

import android.net.Uri
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.*
import androidx.navigation.compose.composable
import com.example.learnretrofit.data.model.User
import com.example.learnretrofit.ui.screens.*
import com.example.learnretrofit.ui.viewmodel.UserViewModel
import com.google.gson.Gson

fun NavGraphBuilder.appNavGraph(navController: NavController, userViewModel: UserViewModel) {
    composable(
        "user/{userId}",
        arguments = listOf(navArgument("userId") { type = NavType.IntType })
    ) { backStackEntry ->
        val userId = backStackEntry.arguments?.getInt("userId") ?: 1
        UserScreen(viewModel = userViewModel, navController = navController, userId = userId)
    }


    composable("history") {
        HistoryScreen(navController = navController, viewModel = userViewModel)
    }

    composable(
        "user_detail/{userJson}",
        arguments = listOf(navArgument("userJson") { type = NavType.StringType })
    ) { backStackEntry ->
        val userJson = backStackEntry.arguments?.getString("userJson") ?: ""
        val user = try {
            Gson().fromJson(Uri.decode(userJson), User::class.java) // Giải mã JSON
        } catch (e: Exception) {
            null
        }

        if (user != null) {
            UserDetailScreen(user = user, navController = navController)
        } else {
            Text("Lỗi tải dữ liệu", modifier = Modifier.fillMaxSize())
        }
    }

}
