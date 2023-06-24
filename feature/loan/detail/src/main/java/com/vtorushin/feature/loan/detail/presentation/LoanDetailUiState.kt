package com.vtorushin.feature.loan.detail.presentation

import com.vtorushin.shared.loan.domain.entity.Loan

sealed interface LoanDetailUiState {
    object Initial : LoanDetailUiState
    object Loading : LoanDetailUiState
    data class Error(val error: String) : LoanDetailUiState
    data class Content(val loan: Loan) : LoanDetailUiState
}