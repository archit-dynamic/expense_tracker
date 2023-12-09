package com.example.expense_tracker.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.expense_tracker.R
import com.example.expense_tracker.composables.custom_composables.CustomButton
import com.example.expense_tracker.composables.custom_composables.CustomInputTextField
import com.example.expense_tracker.composables.custom_composables.VerticalSpace
import com.example.expense_tracker.strings.Routes
import com.example.expense_tracker.ui.theme.Black
import com.example.expense_tracker.ui.theme.ButtonColorGradiant1
import com.example.expense_tracker.ui.theme.Gray
import com.example.expense_tracker.ui.theme.Red

@Composable
fun ForgotPassword(navController: NavHostController) {

    var email by remember { mutableStateOf("") }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(horizontal = 24.dp),
    ) {
        Text("Forgot Password", color = Black, fontSize = 32.sp, fontWeight = FontWeight.W500)
        VerticalSpace(height = 50.dp)
        CustomInputTextField(
            value = email,
            hintText = "Email",
            onValueChange = { email = it },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_username),
                    contentDescription = ""
                )
            },
            modifier = Modifier,
        )
        VerticalSpace(height = 36.dp)
        CustomButton(
            text = "Send",
            suffixIcon = R.drawable.ic_keyboard_right_white,
            onClick = {},
        )
        VerticalSpace(height = 24.dp)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                "Back to ",
                color = Gray,
                fontSize = 16.sp,
                fontWeight = FontWeight.W500,
            )
            Text(
                "Sign In",
                color = ButtonColorGradiant1,
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
        Text(
            "Your new password has been sent to you on your mail.",
            color = Black,
            fontSize = 12.sp,
            fontWeight = FontWeight.W500
        )
    }


}