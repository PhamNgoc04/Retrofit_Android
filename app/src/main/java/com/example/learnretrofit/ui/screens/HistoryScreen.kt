package com.example.learnretrofit.ui.screens

import android.net.Uri
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.learnretrofit.data.model.User
import com.example.learnretrofit.ui.viewmodel.UserViewModel
import com.google.gson.Gson

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryScreen(navController: NavController, viewModel: UserViewModel) {
    val historyList by viewModel.historyUsers.collectAsState(initial = emptyList())

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Lịch sử người dùng",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        if (historyList.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Không có dữ liệu lịch sử",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Gray
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFF5F5F5))
                    .padding(padding)
            ) {
                items(historyList) { user ->
                    HistoryItem(user, navController)
                }
            }
        }
    }
}

@Composable
fun HistoryItem(user: User, navController: NavController) {
    val thumbnailUrl = user.picture?.thumbnail ?: "https://via.placeholder.com/150"

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                val userJson = Uri.encode(Gson().toJson(user))
                navController.navigate("user_detail/$userJson")
            }
            .background(Color.White)
            .padding(12.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberAsyncImagePainter(thumbnailUrl),
                contentDescription = "User Avatar",
                modifier = Modifier
                    .size(55.dp)
                    .clip(CircleShape)
                    .border(2.dp, Color.LightGray, CircleShape)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Column {
                Text(
                    text = "${user.name.first} ${user.name.last}",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = user.email,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Divider(color = Color.LightGray, thickness = 1.dp)
    }
}
