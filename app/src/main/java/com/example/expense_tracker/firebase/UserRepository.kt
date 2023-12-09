package com.example.expense_tracker.firebase

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await

class UserRepository {


    companion object {

        suspend fun createUser(email: String, password: String): FirebaseUser? {
            try {
                return FirebaseAuth
                    .getInstance()
                    .createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            Log.d("Firebase", "User successfully created.")
                        }
                    }
                    .addOnFailureListener {
                        Log.d("Firebase", "Failed to create user: $it")
                    }.await().user
            } catch (e: Exception) {
                Log.d("Firebase", "Error: $e")
                return null
            }
        }

        suspend fun signInUser(email: String, password: String): FirebaseUser? {
            try {
                return FirebaseAuth
                    .getInstance()
                    .signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            Log.d("Firebase", "User successfully signed in.")
                        }
                    }
                    .addOnFailureListener {
                        Log.d("Firebase", "Failed to sign in user: $it")
                    }.await().user
            } catch (e: Exception) {
                Log.d("Firebase", "Error: $e")
                return null
            }
        }

        fun getCurrentUser(): FirebaseUser? {
            return FirebaseAuth.getInstance().currentUser
        }

    }

}