package com.example.expense_tracker.composables.sign_up

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.expense_tracker.enum.SignInState

class SignUpViewModel {

    var username by mutableStateOf("")
    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var showPassword by mutableStateOf(false)
    var signUpState by mutableStateOf(SignInState.UNAUTHENTICATED)
    var showAuthenticationError by mutableStateOf(false)
    var signUpErrorMessage by mutableStateOf("")




}