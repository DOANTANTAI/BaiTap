package com.example.smarttasks.data
import android.content.Context
import com.example.smarttasks.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class AuthManager(private val context: Context) {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    private val webClientId = context.getString(R.string.default_web_client_id)

    private val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken(webClientId)
        .requestEmail()
        .build()

    val googleSignInClient = GoogleSignIn.getClient(context, gso)

    fun signInWithGoogleToken(idToken: String, onComplete: (AuthResult) -> Unit) {
        onComplete(AuthResult.Loading)

        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onComplete(AuthResult.Success)
                } else {
                    onComplete(AuthResult.Error(task.exception?.localizedMessage ?: "Lỗi đăng nhập Firebase"))
                }
            }
    }

    fun getCurrentUser() = auth.currentUser

    fun signOut() {
        auth.signOut()
        googleSignInClient.signOut()
    }
}

