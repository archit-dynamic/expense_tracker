package com.example.expense_tracker.composables.dashboard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MonthlyExpenseGraph() {

    val refreshDataset = remember {
        mutableStateOf(0)
    }

    val modelProducer = remember {
        mutableStateOf(0)
    }

    LaunchedEffect(key1 = refreshDataset.value) {
        // Rebuild Dataset
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
        ) {

        }
        Button(
            onClick = {
                refreshDataset.value = refreshDataset.value + 1
            },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(text = "Refresh")
        }
    }

}