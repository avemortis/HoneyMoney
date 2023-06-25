package com.vtorushin.features.loan.take.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vtorushin.shared.loan.domain.entity.LoanRequest
import com.vtorushin.shared.loan.domain.usecases.CreateLoanUseCase
import com.vtorushin.shared.loan.domain.usecases.GetLoanConditionUseCase
import com.vtorushin.shared.setting.domain.usecases.GetLastNameUseCase
import com.vtorushin.shared.setting.domain.usecases.GetNameUseCase
import com.vtorushin.shared.setting.domain.usecases.GetPhoneNumberUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoanTakeViewModel @Inject constructor(
    private val getLoanConditionUseCase: GetLoanConditionUseCase,
    private val createLoanUseCase: CreateLoanUseCase,
    private val getNameUseCase: GetNameUseCase,
    private val getLastNameUseCase: GetLastNameUseCase,
    private val getPhoneNumberUseCase: GetPhoneNumberUseCase,
    private val router: LoanTakeRouter
) : ViewModel() {
    var inputAmount = String()
    private var maxAmount: Long = 0
    private var percent = 0.0
    private var period = 0
    private val singleStates = MutableSharedFlow<LoanTakeUiState>()
    private val savedStates = MutableStateFlow<LoanTakeUiState>(LoanTakeUiState.Loading)
    val state = merge(singleStates, savedStates)

    fun load() {
        viewModelScope.launch {
            savedStates.emit(LoanTakeUiState.Loading)
            try {
                val condition = getLoanConditionUseCase()
                maxAmount = condition.maxAmount
                percent = condition.percent
                period = condition.period
                savedStates.emit(LoanTakeUiState.State(condition))
            } catch (e: Exception) {
                savedStates.emit(LoanTakeUiState.ServerError)
            }
        }
    }

    fun onTakeLoan() {
        viewModelScope.launch {
            when {
                inputAmount.isBlank() ->
                    singleStates.emit(LoanTakeUiState.EmptyInput)
                inputAmount.toLong() > maxAmount ->
                    singleStates.emit(LoanTakeUiState.TooBigNumber)
                else -> takeLoan()
            }
        }
    }

    fun back() {
        router.backToHistory()
    }

    private suspend fun takeLoan() {
        try {
            createLoanUseCase(
                LoanRequest(
                    inputAmount.toLong(),
                    getNameUseCase(),
                    getLastNameUseCase(),
                    percent,
                    period,
                    getPhoneNumberUseCase()
                )
            )
            savedStates.emit(LoanTakeUiState.Successes)
        } catch (e: Exception) {
            savedStates.emit(LoanTakeUiState.ServerError)
        }
    }


}