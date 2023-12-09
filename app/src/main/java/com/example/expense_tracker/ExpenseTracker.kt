package com.example.expense_tracker

import android.app.Application
import com.google.firebase.FirebaseApp

class ExpenseTracker: Application() {

    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
    }

}