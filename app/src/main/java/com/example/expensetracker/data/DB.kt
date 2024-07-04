package com.example.expensetracker.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.expensetracker.data.data_source.TransactionDAO
import com.example.expensetracker.domain.model.Transaction

@Database(entities = [Transaction::class], version = 1)
abstract class DB : RoomDatabase() {

    abstract val transactionDao: TransactionDAO

}