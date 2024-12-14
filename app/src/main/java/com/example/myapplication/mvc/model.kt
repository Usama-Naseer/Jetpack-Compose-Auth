package com.example.myapplication.mvc

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.State

data class User(val name: String, val age: Int)


class UserViewModel : ViewModel() {
    private val _user = mutableStateOf(User("John Doe", 25))
    val user: State<User> get() = _user

    fun updateUser(name: String, age: Int) {
        _user.value = User(name, age)
    }
}
