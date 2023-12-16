package com.example.expense_tracker.composables.custom_composables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.expense_tracker.colors.AppColors

@Composable
fun CustomTab(
    title: String,
    onClick: () -> Unit,
    isSelected: Boolean = false,
) {
    Box(
        modifier = Modifier
            .border(
                color = AppColors.TabBorder,
                width = 1.dp,
                shape = RoundedCornerShape(size = 1000.dp)
            )
            .background(
                color = if (isSelected) AppColors.TabBorder else AppColors.White,
                shape = RoundedCornerShape(size = 1000.dp)
            )
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable {
                onClick()
            },
        contentAlignment = Alignment.Center,
    ) {
        CustomText(
            text = title,
            fontWeight = FontWeight.W500,
            fontSize = 16.sp,
            color = if (isSelected) AppColors.White else AppColors.Black
        )
    }
}