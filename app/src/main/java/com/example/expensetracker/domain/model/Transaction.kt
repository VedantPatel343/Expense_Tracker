package com.example.expensetracker.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.expensetracker.util.Constants

@Entity
data class Transaction(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val name: String = "",
    val description: String = "",
    val amount: Long = 0L,
    val type: String = Constants.TransactionType.INCOME,
    val mode: String = Constants.TransactionMode.CASH,
    val date: Long = System.currentTimeMillis()
)