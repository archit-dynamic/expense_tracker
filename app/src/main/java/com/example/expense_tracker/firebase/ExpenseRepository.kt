package com.example.expense_tracker.firebase

import android.util.Log
import com.example.expense_tracker.models.Expense
import com.example.expense_tracker.strings.StorageReference
import com.example.expense_tracker.strings.Tags
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class ExpenseRepository {

    companion object{

        fun setUserExpense(expense: Expense, userId: String, epocTime: String): String {

            val firebaseStoreRef =  Firebase.firestore
                .collection(StorageReference.expense)
                .document(userId).collection("userExpenses").document(epocTime)

            return try {
                val result = firebaseStoreRef.set(expense)
                    .addOnSuccessListener {
                        Log.d(Tags.firebase,"Expense successfully added")
                    }
                "Expense successfully added"
            }catch (e: Exception){
                Log.d(Tags.firebase,"Error while adding expense: ${e.localizedMessage}")
                "Oops something went wrong. Please try again."
            }

        }

        suspend fun getUserExpense(expenseId: String): Expense? {

            val firebaseStoreRef = Firebase.firestore
                .collection(StorageReference.expense)
                .document(expenseId)

            return try {
                val result = firebaseStoreRef.get()
                    .addOnSuccessListener {
                        Log.d(Tags.firebase,"Expense fetched successfully")
                    }.await().toObject<Expense>()
                result
            }catch (e: Exception){
                Log.d(Tags.firebase,"Error while fetching expense: ${e.localizedMessage}")
                null
            }

        }

    }

}