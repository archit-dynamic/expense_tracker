package com.example.expense_tracker.composables.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.expense_tracker.composables.ForgotPassword
import com.example.expense_tracker.composables.expense_entry.ExpenseEntryScreen
import com.example.expense_tracker.composables.dashboard.DashBoard
import com.example.expense_tracker.composables.settings.Settings
import com.example.expense_tracker.composables.sign_in.SignIn
import com.example.expense_tracker.composables.sign_up.SignUp
import com.example.expense_tracker.firebase.UserRepository
import com.example.expense_tracker.strings.Routes

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Nav(navController: NavHostController, modifier: Modifier = Modifier) {

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
            DashBoard(modifier = modifier)
        }
        composable(route = Routes.expenseEntry) {
            ExpenseEntryScreen(navController,modifier = modifier)
        }
        composable(route = Routes.settings) {
            Settings(navController,modifier = modifier)
        }

    }

}