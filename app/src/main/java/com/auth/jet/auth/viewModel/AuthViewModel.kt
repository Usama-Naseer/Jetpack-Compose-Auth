package com.auth.jet.auth.viewModel
import androidx.compose.material3.SnackbarDuration
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class AuthViewModel: ViewModel() {

    suspend fun signInWithEmailAndPasswordAwait(
        email: String,
        password: String
    ): AuthResult {
        return suspendCancellableCoroutine { continuation ->
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        print(task.result.user?.email);
                        continuation.resume(task.result)
                    } else {
                        continuation.resumeWithException(task.exception ?: Exception("Unknown error")) // Resume with error
                    }
                }
        }
    }
    suspend fun createUserWithEmailAndPassword(
        email: String,
        password: String,
        name: String,
    ): AuthResult {
        return suspendCancellableCoroutine { continuation ->
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val userId = task.result.user?.uid
                        if (userId != null) {
                            // Create a map for user data
                            val userData = mapOf(
                                "name" to name,
                                "email" to email
                            )
                            // Save user data to Firestore
                            FirebaseFirestore.getInstance()
                                .collection("users")
                                .document(userId)
                                .set(userData)
                                .addOnCompleteListener { dataStoreTask ->
                                    if (dataStoreTask.isSuccessful) {
                                        continuation.resume(task.result) // Resume with success
                                    } else {
                                        continuation.resumeWithException(
                                            dataStoreTask.exception ?: Exception("Failed to store user data")
                                        )
                                    }
                                }
                        } else {
                            continuation.resumeWithException(Exception("User ID is null"))
                        }
                    } else {
                        continuation.resumeWithException(task.exception ?: Exception("User creation failed"))
                    }
                }
        }
    }

}