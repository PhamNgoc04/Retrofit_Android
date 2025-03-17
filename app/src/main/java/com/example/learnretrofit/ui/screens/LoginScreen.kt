package com.example.learnretrofit.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.learnretrofit.R
import com.example.learnretrofit.ui.viewmodel.UserViewModel

@Composable
fun LoginScreen(viewModel: UserViewModel, navController: NavController) {
    var email by remember { mutableStateOf("eve.holt@reqres.in") }
    var password by remember { mutableStateOf("cityslicka") }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Logo trên cùng
        Image(
            painter = painterResource(id = R.drawable.logo_dog),
            contentDescription = "Logo",
            modifier = Modifier
                .size(120.dp)
                .padding(bottom = 16.dp)
        )
        Text(
            text = "D🐾G Life",
            fontSize = 24.sp,
            color = Color(0xFFFF5722)
        )

        Spacer(modifier = Modifier.height(30.dp))

        // Email Input
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Password Input
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Forgot Password
        Text(
            text = "Forgot Password?",
            color = Color.Gray,
            fontSize = 14.sp,
            modifier = Modifier
                .align(Alignment.End)
                .clickable { /* TODO: Xử lý quên mật khẩu */ }
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Nút Login (Gradient)
        Button(
            onClick = {
                viewModel.login(email, password) { userId ->
                    navController.navigate("user/$userId")
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(
                    brush = Brush.horizontalGradient(
                        listOf(Color(0xFFFFA726), Color(0xFFFF5722))
                    ),
                    shape = RoundedCornerShape(12.dp)
                ),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
        ) {
            Text("Login", color = Color.White, fontSize = 16.sp)
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Nút Login với Facebook
        Button(
            onClick = { /* TODO: Xử lý đăng nhập Facebook */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .clip(RoundedCornerShape(12.dp)),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1877F2))
        ) {
            Text("Login With Facebook", color = Color.White, fontSize = 16.sp)
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Dòng chữ "Bạn chưa có tài khoản? Register"
        Row {
            Text("You don't have any account?", color = Color.Gray)
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                "Register",
                color = Color(0xFFFF5722),
                modifier = Modifier.clickable { /* TODO: Xử lý đăng ký */ }
            )
        }
    }
}
