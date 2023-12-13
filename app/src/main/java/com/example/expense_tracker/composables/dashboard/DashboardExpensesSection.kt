package com.example.expense_tracker.composables.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.expense_tracker.composables.custom_composables.CustomText
import com.example.expense_tracker.composables.custom_composables.ExpenseCard
import com.example.expense_tracker.composables.custom_composables.VerticalSpace
import com.example.expense_tracker.models.Expense
import com.example.expense_tracker.utils.CommonFunctions

@Composable
fun DashBoardExpensesSection(
    title: String,
    expenseList: List<Expense>
) {

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
    ) {
        CustomText(text = title)
        VerticalSpace(height = 16.dp)
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(items = expenseList) { expense ->
                ExpenseCard(
                    title = expense.title ?: "",
                    amount = expense.amount ?: "",
                    category = expense.category ?: "",
                    date = expense.date ?: "",
                    icon = CommonFunctions.getCategoryImage("expense")
                )

            }
        }
    }

}