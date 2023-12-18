package com.example.expense_tracker.composables.expense_entry

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.Indication
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.expense_tracker.colors.AppColors
import com.example.expense_tracker.composables.custom_composables.*
import com.example.expense_tracker.strings.AppImages
import java.time.*

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ExpenseEntryScreen(navController: NavHostController,modifier: Modifier = Modifier) {

    val viewModel = viewModel<ExpenseEntryViewModel>()
    val context = LocalContext.current
    val interactionSource = remember { MutableInteractionSource() }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(all = 20.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        Box(
            modifier = Modifier
                .size(height = 36.dp, width = 36.dp)
                .border(color = AppColors.BackCircle.copy(0.1f), width = 2.dp, shape = CircleShape)
                .background(color = AppColors.BackCircle.copy(0.2f), shape = CircleShape)
                .clickable(
                    interactionSource = interactionSource,
                    indication = null
                ) {
                    navController.popBackStack()
                },
            contentAlignment = Alignment.Center,
        ) {
            Image(
                painter = painterResource(id = AppImages.icClose),
                contentDescription = "close expense entry screen",
            )
        }
        VerticalSpace(height = 16.dp)
        CustomText(
            text = "Add new expense",
            fontWeight = FontWeight.Bold,
            color = AppColors.Black,
            fontSize = 24.sp
        )
        VerticalSpace(height = 4.dp)
        CustomText(
            text = "Enter the details of your expense to help you track your spending.",
            fontWeight = FontWeight.W500,
            color = AppColors.Gray,
            fontSize = 16.sp
        )
        VerticalSpace(height = 16.dp)
        CustomBorderedTextFieldWithLabel(
            modifier = Modifier,
            onValueChange = {
                viewModel.amount = it
            },
            value = viewModel.amount,
            label = "Enter Amount",
            hintText = "Enter Amount"
        )
        VerticalSpace(height = 16.dp)
        CustomBorderedTextFieldWithLabel(
            modifier = Modifier,
            onValueChange = {
                viewModel.title = it
            },
            value = viewModel.title,
            label = "Title",
            hintText = "Title",
        )
        VerticalSpace(height = 16.dp)
        CustomBorderedTextFieldWithLabel(
            modifier = Modifier,
            onValueChange = {
                viewModel.description = it
            },
            value = viewModel.description,
            label = "Description (Optional)",
            hintText = "Description",
        )
        VerticalSpace(height = 16.dp)
        CustomDropdown(
            label = "Category",
            list = listOf("education", "entertainment", "expense", "food", "home", "shopping", "travel"),
            onSelect = {
                viewModel.category = it
            }
        )
        VerticalSpace(height = 16.dp)
        CustomDatePicker(
            label = "Date",
            onSelect = {
                viewModel.epocTime = Instant.ofEpochSecond(
                    ZonedDateTime.of(
                        LocalDateTime.of(
                            it,
                            LocalTime.now()
                        ), ZoneId.of(ZoneId.systemDefault().toString())
                    ).toEpochSecond()
                ).toEpochMilli().toString()
                viewModel.date = it.toString()
            }
        )
        VerticalSpace(height = 24.dp)
        CustomButton(
            text = "Add Expense",
            onClick = {
                viewModel.onAddExpenseClick(context = context)
            },
        )
        /*       CustomButton(
                   text = "Get Daily Expenses",
                   onClick = {
                       viewModel.getDailyExpense(userId = UserRepository.getCurrentUser()?.uid ?: "", startDate = "2023-12-01", endDate = "2023-12-15")
                   },
               )
               CustomButton(
                   text = "Get Weekly Expenses",
                   onClick = {
                             viewModel.getLast7DaysBounds()
       //                viewModel.getWeeklyExpense(userId = UserRepository.getCurrentUser()?.uid ?: "", startDate = "2023-12-01", endDate = "2023-12-15")
                   },
               )*/
    }

}