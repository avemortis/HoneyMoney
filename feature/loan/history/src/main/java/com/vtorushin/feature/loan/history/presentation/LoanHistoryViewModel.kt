package com.vtorushin.feature.loan.history.presentation

import androidx.lifecycle.ViewModel
import com.vtorushin.shared.loan.domain.usecases.GetLoansUseCase
import javax.inject.Inject

class LoanHistoryViewModel @Inject constructor(
    private val getLoansUseCase: GetLoansUseCase,
    private val router: LoanHistoryRouter
) : ViewModel() {

}