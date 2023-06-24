package com.vtorushin.feature.loan.detail.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vtorushin.shared.loan.domain.usecases.GetLoanUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoanDetailViewModel @Inject constructor(
    private val getLoanUseCase: GetLoanUseCase,
    private val router: LoanDetailRouter
) : ViewModel() {
    fun loadLoan(id: Int) {
        viewModelScope.launch {
            getLoanUseCase(id)
        }
    }
}