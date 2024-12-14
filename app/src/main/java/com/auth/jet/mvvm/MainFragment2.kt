import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier

@Composable
fun UserListScreen(userViewModel: UserViewModelMV = viewModel()) {
    val users = userViewModel.users.collectAsState().value

    UserList(usersList =users, delete = {id ->
        userViewModel.deleteUser(index = id);

    })
}

@Composable
fun UserList(usersList: List<User>, delete: (id:Int) -> Unit) {
    LazyColumn {
        items(usersList) { user ->
            Text(
                modifier = Modifier.clickable {
                    delete.invoke(usersList.indexOf(user));

                },
                text = user.name)
        }
    }
}
