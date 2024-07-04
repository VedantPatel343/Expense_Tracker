package com.example.expensetracker.presentation

sealed class UiEvents {
    data class ShowToast(val message: String, val isLenShort: Boolean = true) : UiEvents()
}