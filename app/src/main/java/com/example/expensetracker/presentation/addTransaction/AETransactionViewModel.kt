package com.example.expensetracker.presentation.addTransaction

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expensetracker.data.repo.TransactionRepo
import com.example.expensetracker.domain.model.Transaction
import com.example.expensetracker.presentation.UiEvents
import com.example.expensetracker.util.Constant
import com.example.expensetracker.util.Constant.NanArgs.Companion.NAV_TRANSACTION_ID
import com.example.expensetracker.util.Constant.NanArgs.Companion.NAV_TRANSACTION_TYPE
import com.example.expensetracker.util.checkInt
import com.example.expensetracker.util.toFormattedDate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class AETransactionViewModel @Inject constructor(
    private val repository: TransactionRepo,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _name = MutableStateFlow("")
    val name = _name.asStateFlow()

    private val _amount = MutableStateFlow("")
    val amount = _amount.asStateFlow()

    private val _description = MutableStateFlow("")
    val description = _description.asStateFlow()

    private val _transactionType = MutableStateFlow(Constant.TransactionType.INCOME)
    val transactionType = _transactionType.asStateFlow()

    private val _transactionMode = MutableStateFlow(Constant.TransactionMode.CASH)
    val transactionMode = _transactionMode.asStateFlow()

    private val _transactionDate = MutableStateFlow("")
    val transactionDate = _transactionDate.asStateFlow()

    private val _uiEventFlow = MutableSharedFlow<UiEvents>()
    val uiEventFlow = _uiEventFlow.asSharedFlow()

    private var transactionId = -1L

    init {
        savedStateHandle.get<Long>(NAV_TRANSACTION_ID).let { id ->
            transactionId = id ?: -1L
            if (id != -1L) {
                val transaction = repository.getTransactionById(transactionId)
                _name.value = transaction.name
                _amount.value = transaction.amount.toString()
                _description.value = transaction.description
                _transactionType.value = transaction.type
                _transactionMode.value = transaction.mode
                _transactionDate.value = transaction.date.toFormattedDate()
            } else {
                savedStateHandle.get<String>(NAV_TRANSACTION_TYPE).let {
                    _transactionType.value = it ?: Constant.TransactionType.INCOME
                }
                _transactionDate.value = LocalDate.now().toFormattedDate()
            }
        }
    }

    fun onEvents(event: AETransactionEvent) {

        when (event) {
            is AETransactionEvent.OnNameChange -> {
                _name.value = event.name
            }

            is AETransactionEvent.OnModeChange -> {
                _transactionMode.value = event.mode
            }

            is AETransactionEvent.OnAmountChange -> {
                viewModelScope.launch {
                    try {
                        val isValid = checkInt(event.amount)
                        if (isValid || event.amount == "") {
                            _amount.value = event.amount
                        } else {
                            _uiEventFlow.emit(UiEvents.ShowToast("Invalid Character."))
                        }
                    } catch (e: Exception) {
                        _uiEventFlow.emit(UiEvents.ShowToast("Invalid Character."))
                    }
                }
            }

            is AETransactionEvent.OnDescriptionChange -> {
                _description.value = event.description
            }

            is AETransactionEvent.OnSaveBtnClick -> {
                onSaveTransactionClick()
            }
        }

    }

    private fun onSaveTransactionClick() {
        val transaction = if (transactionId != -1L) {
            Transaction(
                id = transactionId,
                name = name.value,
                amount = if (amount.value.isEmpty()) 0 else amount.value.toLong(),
                description = description.value,
                type = transactionType.value,
                mode = transactionMode.value,
                date = LocalDate.parse(transactionDate.value)
            )
        } else {
            Transaction(
                name = name.value,
                amount = if (amount.value.isEmpty()) 0 else amount.value.toLong(),
                description = description.value,
                type = transactionType.value,
                mode = transactionMode.value,
                date = LocalDate.parse(transactionDate.value)
            )
        }
    }

}