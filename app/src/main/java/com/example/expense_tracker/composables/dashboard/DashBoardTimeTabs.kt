package com.example.expense_tracker.composables.dashboard

import android.util.Log
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.expense_tracker.composables.custom_composables.CustomTab
import com.example.expense_tracker.composables.custom_composables.HorizontalSpace
import com.example.expense_tracker.enum.DashboardTab

@Composable
fun DashBoardTimeTabs(onTabClick: (DashboardTab) -> Unit) {

    val scrollState = rememberScrollState(initial = 0)
    var selectedTab by remember {
        mutableStateOf(DashboardTab.LastThirtyDays)
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(state = scrollState),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        CustomTab(
            title = "Today",
            isSelected = selectedTab == DashboardTab.Today,
            onClick = {
                Log.d("Tab", "CLicked Today")
                selectedTab = DashboardTab.Today
                onTabClick(DashboardTab.Today)
            },
        )
        HorizontalSpace(width = 16.dp)
        CustomTab(
            title = "Last 7 days",
            isSelected = selectedTab == DashboardTab.LastSevenDays,
            onClick = {
                Log.d("Tab", "CLicked This week")
                selectedTab = DashboardTab.LastSevenDays
                onTabClick(DashboardTab.LastSevenDays)
            },
        )
        HorizontalSpace(width = 16.dp)
        CustomTab(
            title = "Last 30 days",
            isSelected = selectedTab == DashboardTab.LastThirtyDays,
            onClick = {
                Log.d("Tab", "CLicked This month")
                selectedTab = DashboardTab.LastThirtyDays
                onTabClick(DashboardTab.LastThirtyDays)
            },
        )
        HorizontalSpace(width = 16.dp)
        CustomTab(
            title = "This month",
            isSelected = selectedTab == DashboardTab.ThisMonth,
            onClick = {
                Log.d("Tab", "CLicked Calender")
                selectedTab = DashboardTab.ThisMonth
                onTabClick(DashboardTab.ThisMonth)
            },
        )
    }
}