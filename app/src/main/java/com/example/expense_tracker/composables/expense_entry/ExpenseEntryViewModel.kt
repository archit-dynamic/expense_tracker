package com.example.expense_tracker.composables.expense_entry

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.expense_tracker.firebase.ExpenseRepository
import com.example.expense_tracker.models.Expense
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ExpenseEntryViewModel : ViewModel() {

    var amount by mutableStateOf("")
    var description by mutableStateOf("")
    var category by mutableStateOf("expense")
    var date by mutableStateOf("")

    fun onAddExpenseClick(expense: Expense, userId: String, epocTime: String, context: Context) =
        CoroutineScope(Dispatchers.IO).launch {

            val result = ExpenseRepository.setUserExpense(
                userId = userId,
                epocTime = epocTime,
                expense = expense,
            )
            /*Toast.makeText(context, result,Toast.LENGTH_LONG).show()*/

        }

}