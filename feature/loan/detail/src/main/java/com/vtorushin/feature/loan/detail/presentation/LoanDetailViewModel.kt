package com.vtorushin.feature.loan.detail.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vtorushin.shared.loan.domain.usecases.GetLoanUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoanDetailViewModel @Inject constructor(
    private val getLoanUseCase: GetLoanUseCase,
    private val router: LoanDetailRouter
) : ViewModel() {
    private val _state: MutableStateFlow<LoanDetailUiState> =
        MutableStateFlow(LoanDetailUiState.Loading)

    val state = _state.asStateFlow()

    fun loadLoan(id: Int) {
        viewModelScope.launch {
            _state.emit(LoanDetailUiState.Loading)
            try {
                val loan = getLoanUseCase(id)
                _state.emit(LoanDetailUiState.Content(loan))
            } catch (e: Exception) {
                _state.emit(LoanDetailUiState.Error)
            }
        }
        viewModelScope.launch {
            getLoanUseCase(id)
        }
    }

    fun back() {
        router.backToHistory()
    }
}