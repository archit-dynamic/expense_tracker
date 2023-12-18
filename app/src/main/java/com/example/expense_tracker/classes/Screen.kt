package com.example.expense_tracker.classes

import com.example.expense_tracker.R

sealed class Screen(
    val id: String,
    val title: String,
    val icon: Int,
) {

    object Home : Screen(id = "home", title = "Home", R.drawable.ic_bottom_nav_home)
    object Search : Screen(id = "search", title = "Add Expense", R.drawable.ic_bottom_nav_add_expense)
    object Reports : Screen(id = "profile", title = "Reports", R.drawable.ic_bottom_nav_report)
    object Settings : Screen(id = "settings", title = "Settings", R.drawable.ic_bottom_nav_settings)

    object Items {
        val list = listOf(
            Home, Search, Reports, Settings,
        )
    }

}


