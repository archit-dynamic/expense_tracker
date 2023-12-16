package com.example.expense_tracker.composables.custom_composables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.expense_tracker.colors.AppColors
import com.example.expense_tracker.strings.AppImages
import com.example.expense_tracker.utils.Utils

@Composable
fun ExpenseCard(
    modifier: Modifier = Modifier,
    title: String,
    date: String,
    icon: Int = AppImages.icLauncherBackground,
    amount: String,
    category: String,
) {

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(color = AppColors.ExpenseCardBackground, shape = RoundedCornerShape(10.dp))
            .padding(all = 16.dp)
    ) {

        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CustomAvatar(image = Utils.getCategoryImage(category.lowercase()), radius = 22f, borderColor = AppColors.White)
                HorizontalSpace(width = 10.dp)
                Column(
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    CustomText(text = title, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    CustomText(text = date, fontSize = 14.sp, color = AppColors.Gray)
                }
            }
            CustomText(
                text = "${Utils.getCurrencySymbol()} $amount",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

        }

    }

}