package com.vtorushin.shared.loan.domain.usecases

import com.vtorushin.shared.loan.domain.repository.LoanRepository

class GetLoansUseCase(private val repository: LoanRepository) {
    suspend operator fun invoke() = repository.getLoans()
}