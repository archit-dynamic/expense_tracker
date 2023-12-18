package com.example.expense_tracker.composables.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.expense_tracker.colors.AppColors
import com.example.expense_tracker.composables.custom_composables.CustomText
import com.example.expense_tracker.composables.custom_composables.VerticalSpace
import com.example.expense_tracker.enum.DashboardTab
import com.example.expense_tracker.utils.Utils
import com.patrykandpatrick.vico.core.extension.round

@Composable
fun DashboardExpenseCard(
    amount: String,
    viewModel: DashboardViewModel
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        AppColors.TodayExpenseCard2,
                        AppColors.TodayExpenseCard1
                    ),
                ),
                shape = RoundedCornerShape(16.dp)
            ),
        contentAlignment = Alignment.TopStart
    ) {
        Column(
            modifier = Modifier
                .padding(24.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            CustomText(
                text = "Total Expense: ${Utils.getCurrencySymbol()} ${Utils.roundOffDecimal(amount.toFloat())}",
                color = AppColors.DashboardCardTitle,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            VerticalSpace(height = 8.dp)
            CustomText(
                text = "${if (viewModel.selectedTab == DashboardTab.Today) "" else "Date Range: "}${viewModel.getExpenseSectionTitle().uppercase()}",
                color = AppColors.DashboardCardDetails,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            VerticalSpace(height = 8.dp)
            CustomText(
                text = "Average Daily Expense: ${Utils.getCurrencySymbol()} ${Utils.roundOffDecimal(viewModel.getAverageDailyExpense())}",
                color = AppColors.DashboardCardDetails,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}