package com.example.expense_tracker.firebase

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.expense_tracker.models.AppUser
import com.example.expense_tracker.strings.Routes
import com.example.expense_tracker.strings.StorageReference
import com.example.expense_tracker.strings.Tags
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class UserRepository {


    companion object {

        suspend fun createUser(email: String, password: String): Any? {
            try {
                return FirebaseAuth
                    .getInstance()
                    .createUserWithEmailAndPassword(email.trim(), password.trim())
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            Log.d(Tags.firebase, "User successfully created.")
                        }
                    }
                    .addOnFailureListener {
                        Log.d(Tags.firebase, "Failed to create user: $it")
                    }.await().user
            } catch (e: Exception) {
                Log.d(Tags.firebase, "Error: $e")
                return e.localizedMessage
            }
        }

        suspend fun signInUser(email: String, password: String): FirebaseUser? {
            try {
                return FirebaseAuth
                    .getInstance()
                    .signInWithEmailAndPassword(email.trim(), password.trim())
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            Log.d(Tags.firebase, "User successfully signed in.")
                        }
                    }
                    .addOnFailureListener {
                        Log.d(Tags.firebase, "Failed to sign in user: $it")
                    }.await().user
            } catch (e: Exception) {
                Log.d(Tags.firebase, "Error: $e")
                return null
            }
        }

        suspend fun setUserDetails(user: AppUser){
            val firebaseStoreRef = user.id?.let {
                Firebase.firestore
                    .collection(StorageReference.user)
                    .document(it)
            }
            try {
                firebaseStoreRef?.set(user)
                    ?.addOnSuccessListener {
                        Log.d(Tags.firebase,"User details added successfully")
                    }?.await()
            }catch(e: Exception){
                Log.d(Tags.firebase,"Failed to add user details : $e")
            }
        }

        suspend fun getUserDetails(
            userId: String
        ): AppUser?{
            val firebaseStoreRef =
                Firebase.firestore
                    .collection(StorageReference.user)
                    .document(userId)

            return try {
                val appUser: AppUser? = firebaseStoreRef.get()
                    .addOnSuccessListener {
                        if(it.exists()){
                            Log.d(Tags.firebase,"User data fetched successfully")
                        }else{
                            Log.d(Tags.firebase,"User does not exists")
                        }

                    }.await().toObject<AppUser>()
                appUser
            }catch(e: Exception){
                Log.d(Tags.firebase,"Failed to add user details : $e")
                null
            }
        }

        fun getCurrentUser(): FirebaseUser? {
            return FirebaseAuth.getInstance().currentUser
        }

        fun signOut(navHostController: NavHostController){
            try {
                FirebaseAuth.getInstance().signOut()
                navHostController.navigate(Routes.signIn){
                    popUpTo(navHostController.graph.id){
                        inclusive = true
                    }
                }

            }catch (e: Exception){
                Log.d(Tags.firebase,"$e")
            }
        }

    }

}