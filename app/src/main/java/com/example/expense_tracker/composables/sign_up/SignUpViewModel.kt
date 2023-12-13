package com.example.expense_tracker.composables.sign_up

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.expense_tracker.enum.SignInState
import com.example.expense_tracker.firebase.UserRepository
import com.example.expense_tracker.models.AppUser
import com.example.expense_tracker.strings.Routes
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class SignUpViewModel : ViewModel() {

    var username by mutableStateOf("")
    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var showPassword by mutableStateOf(false)
    var signUpState by mutableStateOf(SignInState.UNAUTHENTICATED)
    var showAuthenticationError by mutableStateOf(false)
    var signUpErrorMessage by mutableStateOf("")

    fun onSignUpClick(navController: NavController) = CoroutineScope(Dispatchers.IO).launch {
        signUpState = SignInState.INPROCESS
        val result: Any? = UserRepository.createUser(email = email, password = password)
        if (result is FirebaseUser?) {
            signUpState = SignInState.AUTHENTICATED
            signUpErrorMessage = ""
            showAuthenticationError = false
            UserRepository.setUserDetails(
                user = AppUser(
                    id = result?.uid,
                    email = email,
                    username = username,
                    createdAt = Date(),
                ),
            )
            CoroutineScope(Dispatchers.Main).launch {
                navController.navigate(Routes.dashboard) {
                    popUpTo(navController.graph.id) {
                        inclusive = true
                    }
                }
            }
        } else {
            Log.d("result", "$result")
            signUpErrorMessage = if ((result is String?) && result != "") {
                result as String
            } else {
                "Oops something went wrong. Please try again"
            }

            showAuthenticationError = true
            signUpState = SignInState.UNAUTHENTICATED
        }
    }

    fun validateForm(): Boolean {
        if (email.trim().isEmpty()) {
            signUpErrorMessage = "Email should not be empty"
            showAuthenticationError = true
            return false
        }
        if (username.trim().isEmpty()) {
            signUpErrorMessage = "Username should not be empty"
            showAuthenticationError = true
            return false
        }
        if (password.trim().isEmpty()) {
            signUpErrorMessage = "Password should not be empty"
            showAuthenticationError = true
            return false
        }
        if (password.trim().length < 6) {
            signUpErrorMessage = "Password should be minimum 6 characters long"
            showAuthenticationError = true
            return false
        }
        showAuthenticationError = false
        return true
    }


}