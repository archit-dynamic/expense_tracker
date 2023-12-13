package com.example.expense_tracker.composables.sign_in

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.expense_tracker.R
import com.example.expense_tracker.animations.LoadingAnimation
import com.example.expense_tracker.colors.AppColors
import com.example.expense_tracker.composables.custom_composables.*
import com.example.expense_tracker.enum.SignInState
import com.example.expense_tracker.firebase.UserRepository
import com.example.expense_tracker.strings.AppImages
import com.example.expense_tracker.strings.Routes
import com.example.expense_tracker.ui.theme.*
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.launch
import java.sql.DriverManager.println

@Composable
fun SignIn(navController: NavHostController) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showPassword by remember { mutableStateOf(false) }
    var signInState by remember { mutableStateOf(SignInState.UNAUTHENTICATED) }
    var showAuthenticationError by remember { mutableStateOf(false) }
    var signInErrorMessage by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()

    fun onSignInClick() {
        var result: FirebaseUser?
        signInState = SignInState.INPROCESS
        coroutineScope.launch {
            result = UserRepository.signInUser(email = email, password = password)
            println("Result: ${result?.email}")
            if (result != null) {
                signInState = SignInState.AUTHENTICATED
                signInErrorMessage = ""
                showAuthenticationError = false
                navController.navigate(Routes.dashboard) {
                    popUpTo(navController.graph.id) {
                        inclusive = true
                    }
                }
            } else {
                println("coroutineScope : Error in signing in")
                signInErrorMessage = "Invalid username or password. Please enter correct credentials."
                showAuthenticationError = true
                signInState = SignInState.UNAUTHENTICATED
            }
        }
    }

    fun validateForm(): Boolean {
        if (email.trim().isEmpty()) {
            signInErrorMessage = "Email should not be empty"
            showAuthenticationError = true
            return false
        }
        if (password.trim().isEmpty()) {
            signInErrorMessage = "Password should not be empty"
            showAuthenticationError = true
            return false
        }
        if (password.trim().length < 6) {
            signInErrorMessage = "Password should be minimum 6 characters long"
            showAuthenticationError = true
            return false
        }
        showAuthenticationError = false
        return true
    }

    if (signInState == SignInState.INPROCESS || signInState == SignInState.AUTHENTICATED) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            LoadingAnimation()
        }
    } else {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxSize()
                .background(color = AppColors.White)
                .padding(horizontal = 24.dp),
        ) {

            Column(
                modifier = Modifier
                    .defaultMinSize(110.dp)
            ) {
                CustomText("Sign In", color = AppColors.Black, fontSize = 32.sp, fontWeight = FontWeight.W500)
                if (showAuthenticationError) VerticalSpace(height = 8.dp)
                if (showAuthenticationError) Row(
                    modifier = Modifier
                        .border(
                            width = 1.dp,
                            color = AppColors.ErrorColor,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .defaultMinSize(60.dp)
                        .background(color = AppColors.ErrorColor.copy(alpha = 0.1f))
                        .padding(all = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Image(
                        painter = painterResource(id = AppImages.icError),
                        contentDescription = "auth error",
                    )
                    HorizontalSpace(width = 10.dp)
                    CustomText(
                        signInErrorMessage,
                        color = AppColors.ErrorColor,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W500,
                    )
                }
            }
            VerticalSpace(height = 8.dp)
            CustomUnderlinedTextField(
                value = email,
                hintText = "Email",
                onValueChange = { email = it },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = AppImages.icUserName),
                        contentDescription = ""
                    )
                },
                modifier = Modifier,
            )
            VerticalSpace(height = 24.dp)
            CustomUnderlinedTextField(
                value = password,
                hintText = "Password",
                onValueChange = { password = it },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = AppImages.icPassword),
                        contentDescription = ""
                    )
                },
                trailingIcon = {
                    Icon(
                        imageVector = if (showPassword) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                        contentDescription = "show password",
                        modifier = Modifier.clickable { showPassword = !showPassword },
                    )
                },
                keyboardType = KeyboardType.Password, showPassword = showPassword,
                modifier = Modifier,
            )
            VerticalSpace(height = 36.dp)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                CustomText(
                    "Forgot Password?",
                    color = AppColors.ButtonColorGradiant1,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W500,
                    modifier = Modifier.clickable {
                        navController.navigate(Routes.forgotPassword) {
                            popUpTo(navController.graph.id) {
                                inclusive = true
                            }
                        }
                    },
                )
            }
            VerticalSpace(height = 24.dp)
            CustomButton(
                text = "Sign In",
                suffixIcon = AppImages.icKeyBoardRightWhite,
                onClick = {
                    if (validateForm()) {
                        onSignInClick()
                    }
                },
            )
            VerticalSpace(height = 24.dp)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                CustomText(
                    "Don't have an account? ",
                    color = AppColors.Gray,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W500,
                )
                CustomText(
                    "Sign Up",
                    color = AppColors.ButtonColorGradiant1,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable {
                        navController.navigate(Routes.signUp) {
                            popUpTo(navController.graph.id) {
                                inclusive = true
                            }
                        }
                    },
                )
            }
        }
    }


}

@Preview(showBackground = true)
@Composable
fun SignInPreview() {
    Expense_trackerTheme {
//        SignIn(navController)
    }
}