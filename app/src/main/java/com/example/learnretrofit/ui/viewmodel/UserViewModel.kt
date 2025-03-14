package com.example.learnretrofit.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.learnretrofit.data.model.User
import com.example.learnretrofit.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UserRepository) : ViewModel() {
    private val _user = MutableStateFlow<User?>(null)
    val user: StateFlow<User?> = _user.asStateFlow()

    private val _historyUsers = MutableStateFlow<List<User>>(emptyList())
    val historyUsers: StateFlow<List<User>> = _historyUsers.asStateFlow()

    fun fetchRandomUser() {
        viewModelScope.launch {
            try {
                val response = repository.getRandomUser()
                Log.d("API Response", "User Data: ${response.results}")

                if (response.results.isNotEmpty()) {
                    val newUser = response.results.first()
                    _user.value = newUser
                    addUserToHistory(newUser)  // 🔹 Thêm vào lịch sử ngay khi lấy dữ liệu
                } else {
                    Log.e("API Error", "Không có dữ liệu user nào trong response")
                }
            } catch (e: Exception) {
                Log.e("API Error", "Lỗi khi lấy dữ liệu: ${e.message}")
            }
        }
    }

    fun addUserToHistory(user: User) {
        viewModelScope.launch {
            _historyUsers.emit(_historyUsers.value + user)  // 🔹 Dùng emit thay vì `.value =`
            Log.d("History", "Đã thêm user vào lịch sử: ${user.name.first} ${user.name.last}")
        }
    }
}
