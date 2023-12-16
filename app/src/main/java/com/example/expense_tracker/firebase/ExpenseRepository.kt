package com.example.expense_tracker.firebase

import android.util.Log
import com.example.expense_tracker.models.Expense
import com.example.expense_tracker.strings.StorageReference
import com.example.expense_tracker.strings.Tags
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import java.text.SimpleDateFormat
import java.util.*

class ExpenseRepository {

    companion object {

        // Function to calculate the start and end dates of the week for a given date
        fun getWeekBounds(date: String): Pair<String, String> {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)
            val calendar = Calendar.getInstance()
            calendar.time = dateFormat.parse(date) ?: Date()

            // Get the day of the week (1 = Sunday, 2 = Monday, ..., 7 = Saturday)
            val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)

            // Calculate the difference between the current day and the start of the week (Sunday)
            val daysUntilStartOfWeek = (dayOfWeek - Calendar.SUNDAY + 7) % 7

            // Calculate the start and end dates of the week
            calendar.add(Calendar.DAY_OF_YEAR, -daysUntilStartOfWeek)
            val startOfWeek = dateFormat.format(calendar.time)

            calendar.add(Calendar.DAY_OF_YEAR, 6)
            val endOfWeek = dateFormat.format(calendar.time)

            return Pair(startOfWeek, endOfWeek)
        }

        fun setUserExpense(expense: Expense, userId: String, epocTime: String): String {

            val firebaseStoreRef = Firebase.firestore
                .collection(StorageReference.expense)
                .document(userId).collection("userExpenses").document(epocTime)

            return try {
                firebaseStoreRef.set(expense)
                    .addOnSuccessListener {
                        Log.d(Tags.firebase, "Expense successfully added")
                    }
                "Expense successfully added"
            } catch (e: Exception) {
                Log.d(Tags.firebase, "Error while adding expense: ${e.localizedMessage}")
                "Oops something went wrong. Please try again."
            }

        }

        suspend fun getUserExpense(userId: String) {

            val firebaseStoreRef = Firebase.firestore
                .collection(StorageReference.expense)
                .document(userId).collection("userExpenses")

            try {
                val result = firebaseStoreRef.get()
                    .addOnSuccessListener {
                        Log.d(Tags.firebase, "Expense fetched successfully")
                    }.await()
                Log.d(
                    Tags.firebase,
                    "getUserExpenseResult: ${result.documents.map { doc -> doc.data }}"
                )
            } catch (e: Exception) {
                Log.d(Tags.firebase, "Error while fetching expense: ${e.localizedMessage}")
            }

        }

        suspend fun getDailyExpense(
            userId: String,
            startDate: String,
            endDate: String
        ): QuerySnapshot? {

            val userExpensesCollection = Firebase.firestore
                .collection("expense")
                .document(userId)
                .collection("userExpenses")

            // Perform a query to retrieve expenses within the date range
            val query = userExpensesCollection
                .whereGreaterThanOrEqualTo("date", startDate)
                .whereLessThanOrEqualTo("date", endDate)


            return query.get()
                .addOnSuccessListener { querySnapshot ->

                    val totalAmountByDate = mutableMapOf<String, Double>()
                    for (document in querySnapshot) {
                        val expense = document.toObject(Expense::class.java)
                        val date = expense.date ?: "Unknown"

                        // Update or initialize the total amount for the date
                        totalAmountByDate[date] =
                            (totalAmountByDate[date] ?: 0.0) + (expense.amount?.toDouble() ?: 0.0)
                    }

                    // Now, totalAmountByDate contains the total amount for each date
                    for ((date, totalAmount) in totalAmountByDate) {
                        println("Date: $date, Total Amount: $totalAmount")
                    }
                }
                .addOnFailureListener { e ->
                    // Handle the error
                    Log.d("getDailyExpense error", "$e")
                }.await()

        }

        fun getWeeklyExpense(
            userId: String,
            startDate: String,
            endDate: String
        ) {

            val userExpensesCollection = Firebase.firestore
                .collection("expense")
                .document(userId)
                .collection("userExpenses")

            // Perform a query to retrieve expenses within the date range
            val query = userExpensesCollection
                .whereGreaterThanOrEqualTo("date", startDate)
                .whereLessThanOrEqualTo("date", endDate)

            query.get()
                .addOnSuccessListener { querySnapshot ->
                    val totalAmountByWeek = mutableMapOf<String, Double>()

                    for (document in querySnapshot) {
                        val expense = document.toObject(Expense::class.java)
                        val date = expense.date ?: "Unknown"

                        // Calculate the start and end dates of the week for the expense
                        val (startOfWeek, _) = getWeekBounds(date)

                        // Update or initialize the total amount for the week
                        totalAmountByWeek[startOfWeek] =
                            (totalAmountByWeek[startOfWeek] ?: 0.0) + (expense.amount?.toDouble()
                                ?: 0.0)
                    }

                    // Now, totalAmountByWeek contains the total amount for each week
                    for ((week, totalAmount) in totalAmountByWeek) {
                        println("Week: $week, Total Amount: $totalAmount")
                    }
                }
                .addOnFailureListener { e ->
                    // Handle the error
                    Log.d("getWeeklyExpense error", "$e")
                }

        }

    }

}