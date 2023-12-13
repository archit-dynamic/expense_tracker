package com.example.expense_tracker.composables.dashboard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.expense_tracker.composables.custom_composables.VerticalSpace
import com.example.expense_tracker.models.Expense
import com.example.expense_tracker.ui.theme.Expense_trackerTheme
import com.google.android.gms.common.util.CollectionUtils.listOf

@Composable
fun DashBoard() {

    val expenseList = listOf(
        Expense(
            title = "Expense 1",
            amount = "100",
            date = "27 Sep 2000",
            category = "Food",
            description = "Food for thought"
        ),
        Expense(
            title = "Expense 1",
            amount = "100",
            date = "27 Sep 2000",
            category = "Food",
            description = "Food for thought"
        ),
        Expense(
            title = "Expense 1",
            amount = "100",
            date = "27 Sep 2000",
            category = "Food",
            description = "Food for thought"
        ),
        Expense(
            title = "Expense 1",
            amount = "100",
            date = "27 Sep 2000",
            category = "Food",
            description = "Food for thought"
        ),
        Expense(
            title = "Expense 1",
            amount = "100",
            date = "27 Sep 2000",
            category = "Food",
            description = "Food for thought"
        ),
        Expense(
            title = "Expense 1",
            amount = "100",
            date = "27 Sep 2000",
            category = "Food",
            description = "Food for thought"
        ),
    )

    Column(
        modifier = Modifier
            .fillMaxSize().fillMaxWidth()
            .padding(all = 20.dp),
    ) {
        DashBoardGreeting()
        VerticalSpace(height = 24.dp)
        DashBoardTimeTabs()
        VerticalSpace(height = 24.dp)
        MonthlyExpenseGraph(bottomLabel = "Hours")
        VerticalSpace(height = 24.dp)
        DashBoardExpensesSection(title = "Today, 3 Oct", expenseList = expenseList)
    }

}

@Preview(showBackground = true)
@Composable
fun DashBoardPreview() {
    Expense_trackerTheme {
        DashBoard()
    }
}