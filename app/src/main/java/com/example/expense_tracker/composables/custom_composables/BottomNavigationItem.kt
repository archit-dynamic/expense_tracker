package com.example.expense_tracker.composables.custom_composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.expense_tracker.classes.Screen
import com.example.expense_tracker.colors.AppColors
import com.example.expense_tracker.ui.theme.Expense_trackerTheme

@Composable
fun BottomNavigationItem(
    item: Screen,
    isSelected: Boolean,
    onClick: () -> Unit,
) {

    val background =
        if (isSelected) AppColors.ButtonColorGradiant1.copy(0.1f) else Color.Transparent
    val contentColor = if (isSelected) AppColors.ButtonColorGradiant1 else AppColors.Black

    Box(
        modifier = Modifier
            .clip(shape = CircleShape)
            .background(background)
            .clickable { onClick() }
    ) {

        Row(
            modifier = Modifier.padding(all = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp),
        ) {

            Icon(painter = painterResource(id = item.icon), contentDescription = item.id, modifier = Modifier.size(height = 24.dp, width = 24.dp))

            AnimatedVisibility(visible = isSelected) {
                CustomText(text = item.title, color = contentColor)
            }

        }

    }

}

@Preview(showBackground = true)
@Composable
fun BottomNavigationItemPreview() {
    Expense_trackerTheme {
        BottomNavigationItem(item = Screen.Home, isSelected = true) {}
    }
}
