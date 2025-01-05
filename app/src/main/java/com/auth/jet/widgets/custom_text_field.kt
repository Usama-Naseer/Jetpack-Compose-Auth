import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun CustomTextField(
    label: @Composable () -> Unit,
    onChange:  (value:String)-> Unit,
    value: String,
    prefix: @Composable () -> Unit,
    transformation: VisualTransformation,
    validation:Validation

    ){
    var errorMessage by remember { mutableStateOf<String?>(null) }

    OutlinedTextField(
        value = value,
        onValueChange = {fv->
            onChange(fv)
            when (validation) {
                Validation.Email -> {
                    if(!isValidEmail(fv)){
                        errorMessage="Invalid email";
                    }
                    else{
                        errorMessage=null;
                    }
                }
                Validation.Password -> {
                    if(!isValidPassword(fv)){
                        errorMessage="Password must be 8 characters long";
                    }
                    else{
                        errorMessage=null;
                    }
                }
                Validation.NotEmpty ->  {
                    if(!isNonEmpty(fv)){
                        errorMessage="Field cannot be empty";
                    }
                    else{
                        errorMessage=null;
                    }
                }
            }
        },
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
    if (errorMessage != null) {
        Text(
            text = errorMessage!!,
            textAlign = TextAlign.Left,
            color = MaterialTheme.colorScheme.error,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.fillMaxWidth()
        )
    }
}
//fun isValidEmail(email: String): Boolean {
//    return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
//}