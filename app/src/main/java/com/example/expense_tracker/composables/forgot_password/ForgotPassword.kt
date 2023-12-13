package com.example.expense_tracker.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.expense_tracker.R
import com.example.expense_tracker.colors.AppColors
import com.example.expense_tracker.composables.custom_composables.CustomButton
import com.example.expense_tracker.composables.custom_composables.CustomText
import com.example.expense_tracker.composables.custom_composables.CustomUnderlinedTextField
import com.example.expense_tracker.composables.custom_composables.VerticalSpace
import com.example.expense_tracker.strings.AppImages
import com.example.expense_tracker.strings.Routes

@Composable
fun ForgotPassword(navController: NavHostController) {

    var email by remember { mutableStateOf("") }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxSize()
            .background(color = AppColors.White)
            .padding(horizontal = 24.dp),
    ) {
        CustomText("Forgot Password", color = AppColors.Black, fontSize = 32.sp, fontWeight = FontWeight.W500)
        VerticalSpace(height = 50.dp)
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
        VerticalSpace(height = 36.dp)
        CustomButton(
            text = "Send",
            suffixIcon = AppImages.icKeyBoardRightWhite,
            onClick = {},
        )
        VerticalSpace(height = 24.dp)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            CustomText(
                "Back to ",
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
                    navController.navigate(Routes.signIn){
                        popUpTo(navController.graph.id){
                            inclusive = true
                        }
                    }
                },
            )
        }
        VerticalSpace(height = 36.dp)
        CustomText(
            "Your new password has been sent to you on your mail.",
            color = AppColors.Black,
            fontSize = 12.sp,
            fontWeight = FontWeight.W500
        )
    }


}