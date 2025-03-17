package com.example.learnretrofit.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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
import com.example.learnretrofit.data.model.User

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserDetailScreen(user: User, navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "${user.first_name} ${user.last_name}",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF1976D2) // Màu xanh dương thay vì gradient
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(40.dp))

            // Ảnh đại diện
            Image(
                painter = rememberAsyncImagePainter(user.avatar),
                contentDescription = "User Avatar",
                modifier = Modifier
                    .size(140.dp)
                    .clip(CircleShape)
                    .shadow(8.dp, CircleShape)
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Tên người dùng
            Text(
                text = "${user.first_name} ${user.last_name}",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Email người dùng
            Text(
                text = user.email,
                fontSize = 18.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(30.dp))

            // Nút quay lại
            Button(
                onClick = { navController.popBackStack() },
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF42A5F5))
            ) {
                Text("Quay lại", color = Color.White, fontSize = 16.sp)
            }
        }
    }
}
