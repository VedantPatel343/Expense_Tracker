package com.example.expensetracker.presentation.addTransaction

sealed class AETransactionEvent {

    data class OnNameChange(val name: String): AETransactionEvent()
    data class OnModeChange(val mode: String): AETransactionEvent()
    data class OnAmountChange(val amount: String): AETransactionEvent()
    data class OnDescriptionChange(val description: String): AETransactionEvent()
    data object OnSaveBtnClick: AETransactionEvent()

}