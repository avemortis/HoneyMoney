package com.vtorushin.feature.loan.detail.presentation

import com.vtorushin.shared.loan.domain.entity.Loan

sealed interface LoanDetailUiState {
    object Loading : LoanDetailUiState
    object Error : LoanDetailUiState
    data class Content(val loan: Loan) : LoanDetailUiState
}