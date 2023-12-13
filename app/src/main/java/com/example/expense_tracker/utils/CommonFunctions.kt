package com.example.expense_tracker.utils

import com.example.expense_tracker.R

class CommonFunctions {

    companion object{
        fun getCategoryImage(category: String) : Int{
            when(category){
                "education"-> return R.drawable.ic_category_education
                "entertainment"-> return R.drawable.ic_category_entertainment
                "food"-> return R.drawable.ic_category_food
                "home"-> return R.drawable.ic_category_home
                "loan"-> return R.drawable.ic_category_loan
                "shopping"-> return R.drawable.ic_category_shopping
                "travel"-> return R.drawable.ic_category_travel
            }
            return R.drawable.ic_category_expense
        }
    }

}