package com.example.expense_tracker.composables.sign_up

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.expense_tracker.animations.LoadingAnimation
import com.example.expense_tracker.colors.AppColors
import com.example.expense_tracker.composables.custom_composables.*
import com.example.expense_tracker.enum.SignInState
import com.example.expense_tracker.strings.AppImages
import com.example.expense_tracker.strings.Routes
import com.example.expense_tracker.ui.theme.Expense_trackerTheme

@Composable
fun SignUp(navController: NavHostController) {

//    var username by remember { mutableStateOf("") }
//    var email by remember { mutableStateOf("") }
//    var password by remember { mutableStateOf("") }
//    var showPassword by remember { mutableStateOf(false) }
//    val signUpState by remember { mutableStateOf(SignInState.UNAUTHENTICATED) }
//    val showAuthenticationError by remember { mutableStateOf(false) }
//    val signUpErrorMessage by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()
    val viewModel = viewModel<SignUpViewModel>()

    /*fun onSignUpClick() {

        var result: Any?
        signUpState = SignInState.INPROCESS
        coroutineScope.launch {
            result = UserRepository.createUser(email = email, password = password)
            if (result is FirebaseUser?) {
                signUpState = SignInState.AUTHENTICATED
                signUpErrorMessage = ""
                showAuthenticationError = false
                navController.navigate(Routes.dashboard) {
                    popUpTo(navController.graph.id) {
                        inclusive = true
                    }
                }
            } else {
                Log.d("result", "$result")
                signUpErrorMessage = if((result is String?) && result != ""){
                    result as String
                }else{
                    "Oops something went wrong. Please try again"
                }

                showAuthenticationError = true
                signUpState = SignInState.UNAUTHENTICATED
            }
        }

    }*/

    /*fun validateForm(): Boolean {
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
    }*/

    if (viewModel.signUpState == SignInState.INPROCESS || viewModel.signUpState == SignInState.AUTHENTICATED) {
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
                CustomText(
                    "Sign Up",
                    color = AppColors.Black,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.W500
                )
                if (viewModel.showAuthenticationError) VerticalSpace(height = 8.dp)
                if (viewModel.showAuthenticationError) Row(
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
                        viewModel.signUpErrorMessage,
                        color = AppColors.ErrorColor,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W500,
                    )
                }
            }
            VerticalSpace(height = 8.dp)
            CustomUnderlinedTextField(
                value = viewModel.username,
                hintText = "Username",
                onValueChange = { viewModel.username = it },
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
                value = viewModel.email,
                hintText = "Email",
                onValueChange = { viewModel.email = it },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = AppImages.icEmail),
                        contentDescription = ""
                    )
                },
                modifier = Modifier,
            )
            VerticalSpace(height = 24.dp)
            CustomUnderlinedTextField(
                value = viewModel.password,
                hintText = "Password",
                onValueChange = { viewModel.password = it },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = AppImages.icPassword),
                        contentDescription = ""
                    )
                },
                trailingIcon = {
                    Icon(
                        imageVector = if (viewModel.showPassword) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                        contentDescription = "show password",
                        modifier = Modifier.clickable {
                            viewModel.showPassword = !viewModel.showPassword
                        },
                    )
                },
                keyboardType = KeyboardType.Password, showPassword = viewModel.showPassword,
                modifier = Modifier,
            )
            VerticalSpace(height = 36.dp)
            CustomButton(
                text = "Sign Up",
                suffixIcon = AppImages.icKeyBoardRightWhite,
                onClick = {
                    if (viewModel.validateForm()) {
                        viewModel.onSignUpClick(navController = navController)
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
                    "Have an account? ",
                    color = AppColors.Gray,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W500,
                )
                CustomText(
                    "Sign In",
                    color = AppColors.ButtonColorGradiant1,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable {
                        navController.navigate(Routes.signIn) {
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
fun SignUpPreview() {
    Expense_trackerTheme {
//        SignUp(navController)
    }
}