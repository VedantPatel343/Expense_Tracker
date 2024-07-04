package com.example.expensetracker.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.expensetracker.data.data_source.TransactionDAO
import com.example.expensetracker.domain.model.Transaction
import com.example.expensetracker.util.LocalDateConverter

@Database(entities = [Transaction::class], version = 1)

@TypeConverters(LocalDateConverter::class)
abstract class DB : RoomDatabase() {

    abstract val transactionDao: TransactionDAO

}