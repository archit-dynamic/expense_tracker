package com.example.expense_tracker.composables.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.expense_tracker.composables.ForgotPassword
import com.example.expense_tracker.composables.home.Home
import com.example.expense_tracker.composables.sign_in.SignIn
import com.example.expense_tracker.composables.sign_up.SignUp
import com.example.expense_tracker.strings.Routes

@Composable
fun Nav(){
    
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.signIn){

        composable(route = Routes.signIn){
            SignIn(navController)
        }
        composable(route = Routes.signUp){
            SignUp(navController)
        }
        composable(route = Routes.forgotPassword){
            ForgotPassword(navController)
        }
        composable(route = Routes.home){
            Home()
        }

    }

}