package com.example.learnretrofit.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.learnretrofit.data.model.User
import com.example.learnretrofit.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UserRepository) : ViewModel() {

    //Lấy ngẫu nhiên người dùng
    private val _loginResult = MutableLiveData<String?>()
    val loginResult: LiveData<String?> = _loginResult

    private val _user = MutableLiveData<User?>()
    val user: LiveData<User?> = _user

    fun login(email: String, password: String, onSuccess: (Int) -> Unit) {
        viewModelScope.launch {
            val response = repository.login(email, password)
            if (response.isSuccessful) {
                _loginResult.value = response.body()?.token
                val userId = (1..12).random() // Chọn ngẫu nhiên ID user từ 1-12
                fetchUser(userId)
                onSuccess(userId)
            } else {
                _loginResult.value = null
            }
        }
    }

    //Xem lịch sử người dùng
    private val _history = MutableStateFlow<List<User>>(emptyList())
    val history: StateFlow<List<User>> = _history.asStateFlow()

    fun fetchUser(userId: Int) {
        viewModelScope.launch {
            val response = repository.getUser(userId)
            if (response.isSuccessful) {
                response.body()?.data?.let { user ->
                    _user.value = user
                    addToHistory(user) // Lưu vào lịch sử
                }
            }
        }
    }

    private fun addToHistory(user: User) {
        val currentList = _history.value ?: emptyList()
        if (!currentList.any { it.id == user.id }) {
            _history.value = currentList + user
        }
    }

    //Xem chi tiết người dùng
    private val _userDetail = MutableStateFlow<User?>(null)
    val userDetail: StateFlow<User?> = _userDetail.asStateFlow()

    fun fetchUserDetail(userId: Int, onSuccess: () -> Unit) {
        viewModelScope.launch {
            val response = repository.getUser(userId)
            if (response.isSuccessful) {
                response.body()?.data?.let { user ->
                    _userDetail.value = user
                    onSuccess()
                }
            }
        }
    }
}
