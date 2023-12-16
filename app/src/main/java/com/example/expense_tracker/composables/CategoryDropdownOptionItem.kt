package com.example.expense_tracker.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.expense_tracker.colors.AppColors
import com.example.expense_tracker.composables.custom_composables.CustomAvatar
import com.example.expense_tracker.composables.custom_composables.CustomText
import com.example.expense_tracker.composables.custom_composables.HorizontalSpace

@Composable
fun CustomDropdownOptionItem(
    categoryImage: Int,
    categoryName: String
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {

        CustomAvatar(image = categoryImage, radius = 16f, borderColor = AppColors.AvatarCircle)
        HorizontalSpace(width = 24.dp)
        CustomText(text = categoryName)
    }

}