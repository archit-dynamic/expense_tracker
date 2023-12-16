package com.example.expense_tracker.composables.dashboard

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.expense_tracker.firebase.ExpenseRepository
import com.example.expense_tracker.firebase.UserRepository
import com.example.expense_tracker.models.Expense
import com.example.expense_tracker.utils.Utils
import com.patrykandpatrick.vico.core.entry.FloatEntry
import java.util.*

class DashboardViewModel: ViewModel() {

    var isChartLoading by mutableStateOf(false)
    var expenseData by mutableStateOf(mutableListOf<Expense>())

    suspend fun getLast30DaysDataPoints(): Pair<ArrayList<FloatEntry>,MutableList<Expense>> {
        var xPos = 0f
        isChartLoading = true
        val dataPoints = arrayListOf<FloatEntry>()
            val result = ExpenseRepository.getDailyExpense(
                userId = UserRepository.getCurrentUser()?.uid ?: "",
                startDate = Utils.getLast30DaysBounds().first,
                endDate = Utils.getLast30DaysBounds().second,
            )
            Log.d("getLast30DaysDataPoints result","$result" )
        val totalAmountByDate = TreeMap<String, Double>(Comparator.reverseOrder())
        val expenseList = mutableListOf<Expense>()
        if(result != null){
            isChartLoading = false
            for (document in result) {
                val expense = document.toObject(Expense::class.java)
                expenseList.add(expense)
                val date = expense.date ?: "Unknown"
                // Update or initialize the total amount for the date
                totalAmountByDate[date] =
                    (totalAmountByDate[date] ?: 0.0) + (expense.amount?.toDouble() ?: 0.0)
            }
            totalAmountByDate.forEach { date, amount ->
                dataPoints.add(FloatEntry(x = xPos, y = amount.toFloat()))
                xPos += 1f
            }
        }else{
            isChartLoading = false
        }
        return Pair(dataPoints,expenseList)
    }

    suspend fun getThisMonthsDataPoints(): Pair<ArrayList<FloatEntry>,MutableList<Expense>> {
        var xPos = 0f
        val dataPoints = arrayListOf<FloatEntry>()
        val result = ExpenseRepository.getDailyExpense(
            userId = UserRepository.getCurrentUser()?.uid ?: "",
            startDate = Utils.getCurrentMonthBounds().first,
            endDate = Utils.getCurrentMonthBounds().second,
        )
        Log.d("getLast7DaysDataPoints result","$result" )
        val totalAmountByDate = TreeMap<String, Double>(Comparator.reverseOrder())
        val expenseList = mutableListOf<Expense>()
        if(result != null){
            for (document in result) {
                val expense = document.toObject(Expense::class.java)
                expenseList.add(expense)
                val date = expense.date ?: "Unknown"

                // Update or initialize the total amount for the date
                totalAmountByDate[date] =
                    (totalAmountByDate[date] ?: 0.0) + (expense.amount?.toDouble() ?: 0.0)
            }

            totalAmountByDate.forEach { date, amount ->
                dataPoints.add(FloatEntry(x = xPos, y = amount.toFloat()))
                xPos += 1f
            }
        }
        return Pair(dataPoints,expenseList)
    }

    suspend fun getTodayDataPoints(): Pair<ArrayList<FloatEntry>,MutableList<Expense>> {
        var xPos = 0f
        val dataPoints = arrayListOf<FloatEntry>()
        val result = ExpenseRepository.getDailyExpense(
            userId = UserRepository.getCurrentUser()?.uid ?: "",
            startDate = Utils.getTodayBounds().first,
            endDate = Utils.getTodayBounds().second,
        )
        Log.d("getLast7DaysDataPoints result","$result" )
        val totalAmountByDate = TreeMap<String, Double>(Comparator.reverseOrder())
        val expenseList = mutableListOf<Expense>()
        if(result != null){
            for (document in result) {
                val expense = document.toObject(Expense::class.java)
                expenseList.add(expense)
                val date = expense.date ?: "Unknown"

                // Update or initialize the total amount for the date
                totalAmountByDate[date] =
                    (totalAmountByDate[date] ?: 0.0) + (expense.amount?.toDouble() ?: 0.0)
            }

            totalAmountByDate.forEach { date, amount ->
                dataPoints.add(FloatEntry(x = xPos, y = amount.toFloat()))
                xPos += 1f
            }
        }
        return Pair(dataPoints,expenseList)
    }

    suspend fun getLast7DaysDataPoints(): Pair<ArrayList<FloatEntry>,MutableList<Expense>> {
        var xPos = 0f
        val dataPoints = arrayListOf<FloatEntry>()
        val result = ExpenseRepository.getDailyExpense(
            userId = UserRepository.getCurrentUser()?.uid ?: "",
            startDate = Utils.getLast7DaysBounds().first,
            endDate = Utils.getLast7DaysBounds().second,
        )
        Log.d("getLast7DaysDataPoints result","$result" )
        val totalAmountByDate = TreeMap<String, Double>(Comparator.reverseOrder())
        val expenseList = mutableListOf<Expense>()
        if(result != null){
            for (document in result) {
                val expense = document.toObject(Expense::class.java)
                expenseList.add(expense)
                val date = expense.date ?: "Unknown"

                // Update or initialize the total amount for the date
                totalAmountByDate[date] =
                    (totalAmountByDate[date] ?: 0.0) + (expense.amount?.toDouble() ?: 0.0)
            }

            totalAmountByDate.forEach { date, amount ->
                dataPoints.add(FloatEntry(x = xPos, y = amount.toFloat()))
                xPos += 1f
            }
        }
        return Pair(dataPoints,expenseList)
    }

}