import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.auth.jet.R
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

@Composable
fun LoginScreen(){
    val email = remember { mutableStateOf("") }
    val pass=remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope() // Create a coroutine scope



    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(19.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        DrawableImageExample()
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = email.value,
            singleLine = true,
            leadingIcon = {
                Icon(Icons.Filled.Email, contentDescription = "Settings")
            },

            onValueChange = {
                email.value = it;
            },
            label = { Text("Enter Email") }

        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = pass.value,
            singleLine = true,
            leadingIcon = {
                Icon(Icons.Rounded.Phone, contentDescription = "Settings")
            },

            onValueChange = {
                pass.value = it;
            },
            label = { Text("Enter Password") }

        )
        ElevatedButton(
            onClick = {
                println("dslksd");

                coroutineScope.launch {
                    try {
                        val result = signInWithEmailAndPasswordAwait(email.value, pass.value)
                        println("Login successful! User: ${result.user?.email}")
                    } catch (e: Exception) {
                        println("Login failed: ${e.message}")
                    }
                }
            },
        ) {
            Text(modifier = Modifier, text = "Login", color = MaterialTheme.colorScheme.primary)
        }

    }

}
@Composable
fun TextFieldWithString() {
    var text by remember { mutableStateOf("") }

    OutlinedTextField(
        value = text,
        onValueChange = { text = it }, // onValueChange expects a String
        label = { Text("Enter Text") },
        modifier = Modifier.fillMaxWidth(),
        singleLine = true
    )
}

fun isValidEmail(email: String): Boolean {
    return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
}
@Composable
fun DrawableImageExample() {
    Image(
        painter = painterResource(id = R.drawable.logo), // Replace with your image name
        contentDescription = "Example Image",
        contentScale = ContentScale.Fit // Adjust scaling as needed
    )
}
suspend fun signInWithEmailAndPasswordAwait(
    email: String,
    password: String
): AuthResult {
    print("dslksd");
    return suspendCancellableCoroutine { continuation ->
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    print(task.result.user?.email);
                    continuation.resume(task.result) // Resume coroutine with result
                } else {
                    continuation.resumeWithException(task.exception ?: Exception("Unknown error")) // Resume with error
                }
            }
    }
}