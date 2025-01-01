package com.auth.jet.auth

import CustomTextField
import android.content.res.Resources.Theme
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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

@Composable
fun SignUpScreen(navController: NavHostController) {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val reEnter = remember { mutableStateOf("") }

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
        CustomTextField(onChange = { password.value = it }, label = { Text("Enter Password") }, value = password.value, prefix = { Icon(Icons.Default.Lock, contentDescription = "pass") }, transformation = PasswordVisualTransformation())
        CustomTextField(onChange = { reEnter.value = it }, label = { Text("Re-Enter Password") }, value = reEnter.value, prefix = { Icon(Icons.Default.Lock, contentDescription = "pass") }, transformation = PasswordVisualTransformation() )




        // Replace wit
    // h your sign up text

    }
}