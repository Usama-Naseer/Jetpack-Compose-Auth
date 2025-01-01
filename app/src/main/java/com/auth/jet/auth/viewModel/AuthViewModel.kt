package com.auth.jet.auth.viewModel
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class AuthViewModel: ViewModel() {

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
}