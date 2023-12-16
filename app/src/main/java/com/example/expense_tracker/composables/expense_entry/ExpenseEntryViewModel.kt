package com.example.expense_tracker.composables.expense_entry

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.expense_tracker.firebase.ExpenseRepository
import com.example.expense_tracker.firebase.UserRepository
import com.example.expense_tracker.models.Expense
import com.patrykandpatrick.vico.core.extension.getFieldValue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDateTime
import java.util.*
import kotlin.math.exp

class ExpenseEntryViewModel : ViewModel() {

    var amount by mutableStateOf("")
    var description by mutableStateOf("")
    var category by mutableStateOf("expense")
    var epocTime by mutableStateOf("")
    var date by mutableStateOf("")

    fun clearData(){
        amount = ""
        description = ""
        category = ""
        epocTime = ""
        date = ""
    }

    fun onAddExpenseClick(context: Context) =
        CoroutineScope(Dispatchers.IO).launch {

            val expense = Expense(
                id = UUID.randomUUID().toString(),
                title = description,
                description = description,
                category = category,
                date = if (date == "") LocalDateTime.now()
                    .toString() else date,
                epocTime = if (epocTime == "") Instant.now().toEpochMilli()
                    .toString() else epocTime,
                amount = amount,
                userid = UserRepository.getCurrentUser()?.uid
            )

            val result = ExpenseRepository.setUserExpense(
                userId = UserRepository.getCurrentUser()?.uid ?: "",
                epocTime = if (epocTime == "") Instant.now().toEpochMilli()
                    .toString() else epocTime,
                expense = expense,
            )
            CoroutineScope(Dispatchers.Main).launch {
                Toast.makeText(context, result, Toast.LENGTH_LONG).show()
            }
        }

    fun getDailyExpense(userId: String, startDate: String, endDate: String) =
        CoroutineScope(Dispatchers.IO).launch {

            ExpenseRepository.getDailyExpense(userId, startDate, endDate)
        }

    fun getWeeklyExpense(userId: String, startDate: String, endDate: String) =
        CoroutineScope(Dispatchers.IO).launch {

            ExpenseRepository.getWeeklyExpense(userId, startDate, endDate)
        }

}