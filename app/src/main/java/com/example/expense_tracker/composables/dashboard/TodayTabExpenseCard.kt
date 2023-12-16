package com.example.expense_tracker.composables.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.modifier.modifierLocalProvider
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.expense_tracker.colors.AppColors
import com.example.expense_tracker.composables.custom_composables.CustomText
import com.example.expense_tracker.composables.custom_composables.VerticalSpace
import com.example.expense_tracker.utils.Utils

@Composable
fun TodayTabExpenseCard(
    amount: String
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                brush = Brush.radialGradient(
                    colors = listOf(
                        AppColors.TodayExpenseCard1,
                        AppColors.TodayExpenseCard2
                    )
                ),
                shape = RoundedCornerShape(16.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CustomText(
                text = "Today's Expense:",
                color = AppColors.White,
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold
            )
            VerticalSpace(height = 24.dp)
            CustomText(
                text = "${Utils.getCurrencySymbol()} $amount",
                color = AppColors.White,
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}