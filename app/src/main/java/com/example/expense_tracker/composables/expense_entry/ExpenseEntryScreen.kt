package com.example.expense_tracker.composables.expense_entry

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.expense_tracker.R
import com.example.expense_tracker.colors.AppColors
import com.example.expense_tracker.composables.custom_composables.*
import com.google.android.gms.common.util.CollectionUtils

@Composable
fun ExpenseEntryScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 20.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        Box(
            modifier = Modifier
                .size(height = 36.dp, width = 36.dp)
                .border(color = AppColors.BackCircle.copy(0.1f), width = 2.dp, shape = CircleShape)
                .background(color = AppColors.BackCircle.copy(0.2f), shape = CircleShape),
            contentAlignment = Alignment.Center,
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_close),
                contentDescription = "close expense entry screen",
            )
        }
        VerticalSpace(height = 16.dp)
        Text(
            text = "Add new expense",
            fontWeight = FontWeight.Bold,
            color = AppColors.Black,
            fontSize = 24.sp
        )
        VerticalSpace(height = 4.dp)
        Text(
            text = "Enter the details of your expense to help you track your spending.",
            fontWeight = FontWeight.W500,
            color = AppColors.Gray,
            fontSize = 16.sp
        )
        VerticalSpace(height = 16.dp)
        CustomBorderedTextFieldWithLabel(
            modifier = Modifier,
            onValueChange = {},
            value = "",
            label = "Enter Amount",
            hintText = "Enter Amount"
        )
        VerticalSpace(height = 16.dp)
        CustomBorderedTextFieldWithLabel(
            modifier = Modifier,
            onValueChange = {},
            value = "",
            label = "Description",
            hintText = "Description",
        )
        VerticalSpace(height = 16.dp)
        CustomDropdown(
            label = "Category",
            list = listOf("first", "second", "third", "fourth")
        )
        VerticalSpace(height = 16.dp)
        CustomDatePicker(
            label = "Date"
        )
        VerticalSpace(height = 24.dp)
        CustomButton(text = "Add Expense", onClick = {})
    }

}