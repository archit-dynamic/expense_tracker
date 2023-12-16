package com.example.expense_tracker.utils

import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

class Utils {

    companion object{
        fun getCurrencySymbol(): String{
            return "\u20B9"
        }

        fun getTodayBounds(): Pair<String, String> {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val calendar = Calendar.getInstance()
            // Set the start date as today
            val startDate = dateFormat.format(calendar.time)
            Log.d("Pair(startDate, endDate)", "StartDate = $startDate and endDate = $startDate")
            return Pair(startDate, startDate)
        }

        fun getLast7DaysBounds(): Pair<String, String> {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val calendar = Calendar.getInstance()
            // Set the end date as today
            val endDate = dateFormat.format(calendar.time)
            // Calculate the start date by subtracting 7 days from today
            calendar.add(Calendar.DAY_OF_YEAR, -6)
            val startDate = dateFormat.format(calendar.time)
            Log.d("Pair(startDate, endDate)", "StartDate = $startDate and endDate = $endDate")
            return Pair(startDate, endDate)
        }

        fun getCurrentMonthBounds(): Pair<String, String> {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)
            val calendar = Calendar.getInstance()

            // Set the calendar to the first day of the current month
            calendar.set(Calendar.DAY_OF_MONTH, 1)
            val startDate = dateFormat.format(calendar.time)

            // Set the calendar to today's date
            calendar.time = Date()
            val endDate = dateFormat.format(calendar.time)

            return Pair(startDate, endDate)
        }

        fun getLast30DaysBounds(): Pair<String, String> {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)
            val calendar = Calendar.getInstance()

            // Set the calendar to today's date
            calendar.time = Date()
            val endDate = dateFormat.format(calendar.time)

            // Subtract 30 days from today's date
            calendar.add(Calendar.DAY_OF_YEAR, -29)
            val startDate = dateFormat.format(calendar.time)

            return Pair(startDate, endDate)
        }

        fun convertNumberToActualDate(number: Int): String {
            val dateFormat = SimpleDateFormat("MMM dd", Locale.US)
            val calendar = Calendar.getInstance()

            // Set the calendar to the current date
            calendar.time = Date()

            // Subtract the corresponding number of days
            calendar.add(Calendar.DAY_OF_YEAR, -number)

            // Format the date
            return dateFormat.format(calendar.time)
        }

    }

}