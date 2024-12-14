import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.AbsoluteCutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.auth.jet.ui.theme.Pink40
import com.auth.jet.ui.theme.Purple40
import com.example.myapplication.R

@Composable
fun LoginScreen(){
    val email = remember { mutableStateOf("") }
    val pass=remember { mutableStateOf("") }

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
            onClick = {},
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