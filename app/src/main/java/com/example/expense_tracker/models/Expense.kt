package com.example.expense_tracker.models

data class Expense(
    var id: String? = null,
    var userid: String? = null,
    var title: String? = null,
    var description: String? = null,
    var amount: String? = null,
    var epocTime: String? = null,
    var date: String? = null,
    var category: String? = null,
)
