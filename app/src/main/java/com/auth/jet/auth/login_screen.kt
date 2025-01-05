import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.auth.jet.R
import com.auth.jet.auth.viewModel.AuthViewModel
import kotlinx.coroutines.launch


@Composable
fun LoginScreen(navController: NavHostController,authViewModel: AuthViewModel) {
    val email = remember { mutableStateOf("") }
    val pass=remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()
    val snackBarHostState = remember { SnackbarHostState() }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(19.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        DrawableImageExample()
        CustomTextField(onChange = {
            email.value = it
        }, label = { Text("Enter Email") }, value = email.value, prefix = {Icon(Icons.Rounded.Phone, contentDescription = "Email")
        } , transformation = VisualTransformation.None , validation = Validation.Email)
        CustomTextField(onChange = {
            pass.value = it
        }, label = { Text("Enter password") }, value = pass.value, prefix = {Icon(Icons.Filled.Search, contentDescription = "Password")},transformation = PasswordVisualTransformation(),validation = Validation.Password)

        ElevatedButton(
            onClick = {
                coroutineScope.launch {
                    try {
                       authViewModel.signInWithEmailAndPasswordAwait(email.value, pass.value)
                        snackBarHostState.showSnackbar("Login Successful",
                            actionLabel = "Dismiss", // Ensure this is a non-null String
                            duration = SnackbarDuration.Short

                        )
                    } catch (e: Exception) {
                            snackBarHostState.showSnackbar("Login failed ${e.message}",
                                    actionLabel = "retry", // Ensure this is a non-null String
                                duration = SnackbarDuration.Short
                            )
                    }
                }
            },
        ) {
            Text(modifier = Modifier, text = "Login", color = MaterialTheme.colorScheme.primary)
        }
        Row {
            Text(
                text = "Don't have an account?",
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                modifier = Modifier.padding(start = 16.dp)
            )
            Text(
                modifier = Modifier.clickable {
                    navController.navigate("signup")
                }.padding(start = 4.dp),

                text = "Sign Up",
                color = MaterialTheme.colorScheme.primary,
            )
        }

        SnackbarHost(hostState = snackBarHostState, modifier = Modifier.fillMaxWidth())


    }

}


@Composable
fun DrawableImageExample() {
    Image(
        modifier = Modifier.size(200.dp, 200.dp), // Set your desired image size
        painter = painterResource(id = R.drawable.logo), // Replace with your image name
        contentDescription = "Example Image",
        contentScale = ContentScale.Fit // Adjust scaling as needed
    )
}
