package com.example.expense_tracker.composables.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.expense_tracker.composables.ForgotPassword
import com.example.expense_tracker.composables.expense_entry.ExpenseEntryScreen
import com.example.expense_tracker.composables.dashboard.DashBoard
import com.example.expense_tracker.composables.settings.Settings
import com.example.expense_tracker.composables.sign_in.SignIn
import com.example.expense_tracker.composables.sign_up.SignUp
import com.example.expense_tracker.firebase.UserRepository
import com.example.expense_tracker.strings.Routes

@Composable
fun Nav(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination =
        if (UserRepository.getCurrentUser() != null)
            Routes.dashboard
        else
            Routes.signIn,
    ) {

        composable(route = Routes.signIn) {
            SignIn(navController)
        }
        composable(route = Routes.signUp) {
            SignUp(navController)
        }
        composable(route = Routes.forgotPassword) {
            ForgotPassword(navController)
        }
        composable(route = Routes.dashboard) {
            DashBoard()
        }
        composable(route = Routes.expenseEntry) {
            ExpenseEntryScreen()
        }
        composable(route = Routes.settings) {
            Settings(navController)
        }

    }

}