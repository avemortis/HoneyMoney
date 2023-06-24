package com.vtorushin.feature.loan.history.presentation

import com.vtorushin.shared.loan.domain.entity.Loan

interface LoanHistoryUiState {
    object Loading : LoanHistoryUiState
    object EmptyList : LoanHistoryUiState
    data class Content(private val loans: List<Loan>)
    data class Error(private val errorText: String)
}