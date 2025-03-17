package com.example.learnretrofit.ui.screens

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.History
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.learnretrofit.ui.viewmodel.UserViewModel
import com.google.gson.Gson

@Composable
fun UserScreen(viewModel: UserViewModel, navController: NavController, userId: Int) {
    val user by viewModel.user.observeAsState()

    LaunchedEffect(userId) {
        viewModel.fetchUser(userId)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Thanh công cụ chứa nút quay lại và icon lịch sử
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Nút quay lại
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.Black)
            }

            // Nút lịch sử (góc trên bên phải)
            val history by viewModel.history.collectAsState()
            if (history.isNotEmpty()) {
                IconButton(onClick = { navController.navigate("history") }) {
                    Icon(Icons.Default.History, contentDescription = "History", tint = Color.Black)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        user?.let {
            // Ảnh đại diện
            Image(
                painter = rememberAsyncImagePainter(it.avatar),
                contentDescription = "Avatar",
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
                    .shadow(4.dp, CircleShape)
                    .clickable {
                        navController.navigate("user_detail/${Uri.encode(Gson().toJson(user))}")
                    }
            )
            Spacer(modifier = Modifier.height(10.dp))

            // Tên người dùng
            Text(
                text = "${it.first_name} ${it.last_name}",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            // Email người dùng
            Text(
                text = it.email,
                fontSize = 16.sp,
                color = Color.Gray
            )
        } ?: Text("Đang tải...", fontSize = 18.sp, color = Color.Gray)

        Spacer(modifier = Modifier.height(30.dp))

        // Nút lấy người dùng ngẫu nhiên
        Button(
            onClick = {
                val randomUserId = (1..12).random()
                viewModel.fetchUser(randomUserId)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(
                    brush = Brush.horizontalGradient(
                        listOf(Color(0xFF42A5F5), Color(0xFF1976D2))
                    ),
                    shape = RoundedCornerShape(12.dp)
                ),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
        ) {
            Text(text = "Lấy Người Dùng Ngẫu Nhiên", color = Color.White, fontSize = 16.sp)
        }
    }
}
