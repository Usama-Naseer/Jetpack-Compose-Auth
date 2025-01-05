package com.auth.jet.auth

import CustomTextField
import android.content.res.Resources.Theme
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp

import androidx.navigation.NavHostController
import com.auth.jet.R
import com.auth.jet.auth.viewModel.AuthViewModel
import kotlinx.coroutines.launch

@Composable
fun SignUpScreen(navController: NavHostController,authViewModel: AuthViewModel) {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val reEnter = remember { mutableStateOf("") }
    val fullName = remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()
    val snackBarHostState = remember { SnackbarHostState() }


    Column(
        horizontalAlignment =  Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize().padding(20.dp),
        verticalArrangement =  Arrangement.spacedBy(20.dp)
    ) {
        Image(
            modifier = Modifier.size(200.dp,200.dp),
            painter = painterResource(id = R.drawable.logo), // Replace with your image name
            contentDescription = "Logo",
            contentScale = ContentScale.Fit
        )
        Text(text = "Create Account!", style = TextStyle(fontSize = TextUnit(value = 20.0F, type = TextUnitType.Sp), color = MaterialTheme.colorScheme.secondary))
        CustomTextField(onChange = { email.value = it }, label = { Text("Enter Email") }, value = email.value , prefix  = { Icon(Icons.Default.Email, contentDescription = "Em") }, transformation = VisualTransformation.None)
        CustomTextField(onChange = { fullName.value = it }, label = { Text("Full name") }, value = fullName.value , prefix  = { Icon(Icons.Filled.Person, contentDescription = "name") }, transformation = VisualTransformation.None)
        CustomTextField(onChange = { password.value = it }, label = { Text("Enter Password") }, value = password.value, prefix = { Icon(Icons.Default.Lock, contentDescription = "pass") }, transformation = PasswordVisualTransformation())
        CustomTextField(onChange = { reEnter.value = it }, label = { Text("Re-Enter Password") }, value = reEnter.value, prefix = { Icon(Icons.Default.Lock, contentDescription = "pass") }, transformation = PasswordVisualTransformation() )
        ElevatedButton(
            onClick = {
                coroutineScope.launch {
                    try {
                        authViewModel.createUserWithEmailAndPassword(email.value, password.value, name = fullName.value)
                        snackBarHostState.showSnackbar("Signup Successful",
                            actionLabel = "Dismiss", // Ensure this is a non-null String
                            duration = SnackbarDuration.Short

                        )
                    } catch (e: Exception) {
                        snackBarHostState.showSnackbar("${e.message}",
                            actionLabel = "retry", // Ensure this is a non-null String
                            duration = SnackbarDuration.Short
                        );
                    }
                }
            },
        ) {
            Text(modifier = Modifier, text = "Sign up", color = MaterialTheme.colorScheme.primary)
        }
        Row {
            Text(
                text = "Already have an account?",
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                modifier = Modifier.padding(start = 16.dp)
            )
            Text(
                modifier = Modifier.clickable {
                    navController.popBackStack()
                }.padding(start = 4.dp),

                text = "Login",
                color = MaterialTheme.colorScheme.primary,
            )
        }

        SnackbarHost(hostState = snackBarHostState, modifier = Modifier.fillMaxWidth())


    }
}