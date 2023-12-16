package com.example.expense_tracker.composables.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.expense_tracker.composables.custom_composables.CustomButton
import com.example.expense_tracker.firebase.UserRepository

@Composable
fun Settings(navHostController: NavHostController) {

    Column(
        modifier = Modifier.fillMaxSize().padding(all = 20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CustomButton(text = "Logout") {
            UserRepository.signOut(navHostController = navHostController)
        }
    }

}