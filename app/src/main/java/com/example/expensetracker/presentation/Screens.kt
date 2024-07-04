package com.example.expensetracker.presentation

import com.example.expensetracker.util.Constant
import com.example.expensetracker.util.Constant.NanArgs.Companion.NAV_TRANSACTION_ID
import com.example.expensetracker.util.Constant.NanArgs.Companion.NAV_TRANSACTION_TYPE
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data object Home

@Serializable
data object TransactionHistory

@Serializable
data class AETransaction(
    @SerialName(NAV_TRANSACTION_ID) val transactionId: Long = -1L,
    @SerialName(NAV_TRANSACTION_TYPE) val transactionType: String = Constant.TransactionType.INCOME
)