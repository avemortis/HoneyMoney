package com.vtorushin.features.loan.take.presentation

import com.vtorushin.shared.loan.domain.entity.Loan

sealed interface LoanTakeUiState {
    object EmptyInput : LoanTakeUiState
    object TooBigNumber : LoanTakeUiState
    data class Successes(val loan: Loan) : LoanTakeUiState
    object Error : LoanTakeUiState
}