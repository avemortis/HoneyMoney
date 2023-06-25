package com.vtorushin.features.loan.take.presentation

import androidx.lifecycle.ViewModel
import com.vtorushin.shared.loan.domain.usecases.CreateLoanUseCase
import com.vtorushin.shared.loan.domain.usecases.GetLoanConditionUseCase
import javax.inject.Inject

class LoanTakeViewModel @Inject constructor(
    private val getLoanConditionUseCase: GetLoanConditionUseCase,
    private val createLoanUseCase: CreateLoanUseCase,
    private val router: LoanTakeRouter
) : ViewModel() {

}