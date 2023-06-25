package com.vtorushin.features.loan.take.presentation

import com.vtorushin.shared.loan.domain.entity.LoanCondition

sealed interface LoanTakeUiState {
    object EmptyInput : LoanTakeUiState
    object TooBigNumber : LoanTakeUiState
    data class State(val loanCondition: LoanCondition) : LoanTakeUiState
    object Successes : LoanTakeUiState
    object ServerError : LoanTakeUiState
    object Loading : LoanTakeUiState
}