package com.example.expensetracker.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.expensetracker.util.Constant
import java.time.LocalDate

@Entity
data class Transaction(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val name: String = "",
    val description: String = "",
    val amount: Long = 0L,
    val type: String = Constant.TransactionType.INCOME,
    val mode: String = Constant.TransactionMode.CASH,
    val date: LocalDate
)