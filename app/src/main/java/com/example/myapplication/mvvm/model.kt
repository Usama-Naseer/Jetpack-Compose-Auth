import androidx.compose.runtime.snapshots.SnapshotStateList

data class User(val id: Int, val name: String)

class UserRepository {
    fun getUsers(): List<User> {
        return listOf(
            User(1, "Alice"),
            User(2, "Bob"),
            User(3, "Charlie")
        )
    }

}
