import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.myapplication.mvc.UserViewModel

@Composable
fun UserView(viewModel: UserViewModel) {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text("Name: ${viewModel.user.value.name}")
        Text("Age: ${viewModel.user.value.age}")

        var name by remember { mutableStateOf(viewModel.user.value.name) }
        var age by remember { mutableStateOf(viewModel.user.value.age.toString()) }

        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Enter Name") }
        )
        TextField(
            value = age,
            onValueChange = { age = it },
            label = { Text("Enter Age") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Button(onClick = { age.toIntOrNull()?.let { viewModel.updateUser(name=name, age = it) } }) {
            Text("Update")
        }
    }
}
