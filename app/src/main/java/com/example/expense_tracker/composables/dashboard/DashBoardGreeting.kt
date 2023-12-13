package com.example.expense_tracker.composables.dashboard

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.expense_tracker.R
import com.example.expense_tracker.colors.AppColors
import com.example.expense_tracker.composables.custom_composables.CustomAvatar
import com.example.expense_tracker.composables.custom_composables.CustomText
import com.example.expense_tracker.composables.custom_composables.HorizontalSpace
import com.example.expense_tracker.strings.AppImages

@Composable
fun DashBoardGreeting() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        CustomAvatar(
            url = "",
            image = AppImages.icLauncherBackground,
            radius = 24f,
            borderColor = Color.Transparent
        )
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier.padding(horizontal = 10.dp)
        ) {
            CustomText(
                text = "Good Morning, Archit",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            CustomText(
                text = "Track your expenses, start your day right",
                fontSize = 14.sp,
                color = AppColors.Gray,
                fontWeight = FontWeight.W500
            )
        }
    }
}