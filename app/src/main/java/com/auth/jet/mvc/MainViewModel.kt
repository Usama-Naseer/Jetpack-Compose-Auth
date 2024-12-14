package com.auth.jet.mvc

import UserView
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun UserController(viewModel: UserViewModel = viewModel()) {
    // Observe the user state
    val user by viewModel.user

    // Pass the state and update function to the UserView
    UserView(viewModel)
}