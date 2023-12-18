package com.example.expense_tracker.composables.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.expense_tracker.composables.custom_composables.CustomButton
import com.example.expense_tracker.composables.custom_composables.CustomText
import com.example.expense_tracker.firebase.UserRepository

@Composable
fun Settings(navHostController: NavHostController,modifier: Modifier = Modifier) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(all = 20.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        CustomText(text = "Settings", fontSize = 24.sp)
    }

}