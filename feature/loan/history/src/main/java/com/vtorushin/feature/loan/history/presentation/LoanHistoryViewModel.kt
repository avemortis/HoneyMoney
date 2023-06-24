package com.vtorushin.feature.loan.history.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vtorushin.shared.loan.domain.usecases.GetLoansUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

class LoanHistoryViewModel @Inject constructor(
    private val getLoansUseCase: GetLoansUseCase,
    private val router: LoanHistoryRouter
) : ViewModel() {
    private val _state: MutableStateFlow<LoanHistoryUiState> = MutableStateFlow(LoanHistoryUiState.Loading)
    val state = _state.asStateFlow()

    fun overviewLoan(loanId: Int) {
        router.overviewLoan(loanId)
    }

    fun refresh() {
        viewModelScope.launch {
            _state.emit(LoanHistoryUiState.Loading)
            try {
                val loans = getLoansUseCase()
                if (loans.isEmpty())
                    _state.emit(LoanHistoryUiState.EmptyList)
                else
                    _state.emit(LoanHistoryUiState.Content(loans))
            } catch (e: Exception) {
                _state.emit(LoanHistoryUiState.ServerError)
            }
        }
    }

    init {
        refresh()
    }
}