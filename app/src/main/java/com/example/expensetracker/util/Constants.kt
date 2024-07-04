package com.example.expensetracker.util

object Constants {

    interface TransactionType {
        companion object {
            const val INCOME = "Income"
            const val EXPENSE = "Expense"
        }
    }

    interface TransactionMode {
        companion object {
            const val CASH = "Cash"
            const val ONLINE = "Online"
        }
    }

}