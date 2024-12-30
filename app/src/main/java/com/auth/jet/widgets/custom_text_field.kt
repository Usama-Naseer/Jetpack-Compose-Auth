import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun CustomTextField(
    label: @Composable () -> Unit,
    onChange:  (value:String)-> Unit,
    value: String,
    prefix: @Composable () -> Unit,
    transformation: VisualTransformation

    ){
    OutlinedTextField(
        value = value,
        onValueChange = onChange,
        label = label,
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Email
        ),
        shape =  RoundedCornerShape(20.dp),
        prefix = prefix,
        visualTransformation = transformation
    )
}