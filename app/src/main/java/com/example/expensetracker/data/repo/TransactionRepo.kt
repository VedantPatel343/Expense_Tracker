package com.example.expensetracker.data.repo

import com.example.expensetracker.data.data_source.TransactionDAO
import com.example.expensetracker.domain.model.Transaction

class TransactionRepo(private val dao: TransactionDAO) {

    fun getTransactions() = dao.getTransactions()

    fun getTransactionById(id: Long) = dao.getTransactionById(id)

    suspend fun upsertTransaction(transaction: Transaction) = dao.upsertTransaction(transaction)

    suspend fun deleteTransaction(transaction: Transaction) = dao.deleteTransaction(transaction)

}