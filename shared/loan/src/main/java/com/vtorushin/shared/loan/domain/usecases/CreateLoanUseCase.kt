package com.vtorushin.shared.loan.domain.usecases

import com.vtorushin.shared.loan.domain.entity.LoanRequest
import com.vtorushin.shared.loan.domain.repository.LoanIssueRepository

class CreateLoanUseCase(private val repository: LoanIssueRepository) {
    suspend operator fun invoke(request: LoanRequest) = repository.requestLoan(request)
}