package com.example.expense_tracker.composables.custom_composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

@Composable
fun VerticalSpace(height: Dp){
    Box (modifier = Modifier.height(height))
}

@Composable
fun HorizontalSpace(width: Dp){
    Box (modifier = Modifier.width(width))
}