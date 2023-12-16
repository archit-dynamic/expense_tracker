package com.example.expense_tracker.composables.custom_composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.expense_tracker.classes.Screen
import com.example.expense_tracker.ui.theme.Expense_trackerTheme

@Composable
fun CustomBottomNavigation(
    currentScreenId: String,
    onItemSelect: (Screen) -> Unit
) {

    val items = Screen.Items.list

    Row(
        modifier = Modifier
            .background(MaterialTheme.colors.background)
            .padding(all = 8.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {

        items.forEach{ item->
            BottomNavigationItem(item = item, isSelected = item.id == currentScreenId ) {
                onItemSelect(item)
            }
        }

    }

}

@Preview(showBackground = true)
@Composable
fun CustomBottomNavigationPreview() {
    Expense_trackerTheme {
        CustomBottomNavigation(currentScreenId = Screen.Home.id) {}
    }
}
