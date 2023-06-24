package com.vtorushin.feature.loan.history.presentation

import com.vtorushin.shared.loan.domain.entity.Loan

interface LoanHistoryUiState {
    object Loading : LoanHistoryUiState
    object EmptyList : LoanHistoryUiState
    data class Content(val loans: List<Loan>) : LoanHistoryUiState
    object ServerError : LoanHistoryUiState
}