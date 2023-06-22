package com.vtorushin.shared.loan.domain.usecases

import com.vtorushin.shared.loan.domain.repository.LoanRepository

class GetLoanUseCase(private val repository: LoanRepository) {
    suspend operator fun invoke(id: Int) = repository.getLoan(id)
}