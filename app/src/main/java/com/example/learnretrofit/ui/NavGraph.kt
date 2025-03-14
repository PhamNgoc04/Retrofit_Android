package com.example.learnretrofit.ui

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.example.learnretrofit.data.model.User
import com.example.learnretrofit.ui.screens.*
import com.example.learnretrofit.ui.viewmodel.UserViewModel
import com.google.gson.Gson

fun NavGraphBuilder.appNavGraph(navController: NavController, userViewModel: UserViewModel) {
    composable("user") {
        UserScreen(viewModel = userViewModel, navController = navController)
    }

    composable("history") {
        HistoryScreen(navController, viewModel = userViewModel)
    }

    composable(
        "user_detail/{userJson}",
        arguments = listOf(navArgument("userJson") { type = NavType.StringType })
    ) { backStackEntry ->
        val userJson = backStackEntry.arguments?.getString("userJson")
        val user = Gson().fromJson(userJson, User::class.java)
        UserDetailScreen(user = user, navController = navController)
    }
}
