package com.example.expense_tracker.composables.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.expense_tracker.composables.custom_composables.VerticalSpace
import com.example.expense_tracker.ui.theme.Expense_trackerTheme

@Composable
fun DashBoard(modifier: Modifier = Modifier) {

    val viewModel = viewModel<DashboardViewModel>()


    Column(
        modifier = modifier
            .fillMaxSize()
            .fillMaxWidth()
            .padding(start = 20.dp, top = 20.dp, end = 20.dp),
    ) {
        DashBoardGreeting()
        VerticalSpace(height = 24.dp)
        DashBoardTimeTabs(onTabClick = {
            viewModel.selectedTab = it
        })
        VerticalSpace(height = 24.dp)
        DashboardExpenseCard(
            amount = viewModel.getTotalExpense().toString(),
            viewModel = viewModel,
        )
        VerticalSpace(height = 8.dp)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,
        ) {
            MonthlyExpenseGraph(
                bottomLabel = viewModel.bottomAxisLAbel(),
                viewModel = viewModel,
                tab = viewModel.selectedTab
            )
            VerticalSpace(height = 24.dp)
            DashBoardExpensesSection(
                title = viewModel.getExpenseSectionTitle().uppercase(),
                expenseList = viewModel.expenseData,
                viewModel = viewModel,
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun DashBoardPreview() {
    Expense_trackerTheme {
        DashBoard()
    }
}