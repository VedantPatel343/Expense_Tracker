package com.example.expensetracker.di

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import com.example.expensetracker.data.DB
import com.example.expensetracker.data.repo.TransactionRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Module {

    @Provides
    @Singleton
    fun getDB(app: Application): DB {
        return Room.databaseBuilder(
            app,
            DB::class.java,
            "expense_tracker_db"
        )
            .build()
    }

    @Provides
    @Singleton
    fun getTransactionRepo(db: DB): TransactionRepo {
        return TransactionRepo(db.transactionDao)
    }

}