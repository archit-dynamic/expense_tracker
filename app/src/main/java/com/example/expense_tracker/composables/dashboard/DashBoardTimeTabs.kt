package com.example.expense_tracker.composables.dashboard

import android.util.Log
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.expense_tracker.composables.custom_composables.CustomTab
import com.example.expense_tracker.composables.custom_composables.HorizontalSpace
import com.example.expense_tracker.firebase.UserRepository

@Composable
fun DashBoardTimeTabs() {

    val scrollState = rememberScrollState(initial = 0)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(state = scrollState),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        CustomTab(
            title = "Today",
            onClick = {
                Log.d("Tab", "CLicked Today")
                UserRepository.signOut()
            },
        )
        HorizontalSpace(width = 16.dp)
        CustomTab(title = "This week", onClick = { Log.d("Tab", "CLicked This week") })
        HorizontalSpace(width = 16.dp)
        CustomTab(title = "This month", onClick = { Log.d("Tab", "CLicked This month") })
        HorizontalSpace(width = 16.dp)
        CustomTab(title = "Calender", onClick = { Log.d("Tab", "CLicked Calender") })
    }
}