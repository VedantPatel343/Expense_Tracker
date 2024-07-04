package com.example.expensetracker.data.data_source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.expensetracker.domain.model.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDAO {

    @Query("SELECT * FROM `Transaction`")
    fun getTransactions(): Flow<List<Transaction>>

    @androidx.room.Transaction
    @Upsert
    suspend fun upsertTransaction(transaction: Transaction)

    @androidx.room.Transaction
    @Delete
    suspend fun deleteTransaction(transaction: Transaction)


}