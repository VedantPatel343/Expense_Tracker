package com.example.expensetracker.util

object Constant {

    interface NanArgs {
        companion object {
            const val  NAV_TRANSACTION_ID = "nav_transaction_id"
            const val  NAV_TRANSACTION_TYPE = "nav_transaction_type"
        }
    }

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