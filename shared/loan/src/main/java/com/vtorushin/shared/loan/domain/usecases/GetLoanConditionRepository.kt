package com.vtorushin.shared.loan.domain.usecases

import com.vtorushin.shared.loan.domain.repository.LoanConditionRepository

class GetLoanConditionRepository(private val repository: LoanConditionRepository) {
    suspend operator fun invoke() = repository.getLoanCondition()
}