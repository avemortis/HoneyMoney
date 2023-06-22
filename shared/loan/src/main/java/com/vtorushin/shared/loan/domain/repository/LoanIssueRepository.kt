package com.vtorushin.shared.loan.domain.repository

import com.vtorushin.shared.loan.domain.entity.Loan
import com.vtorushin.shared.loan.domain.entity.LoanRequest

interface LoanIssueRepository {
    suspend fun requestLoan(request: LoanRequest): Loan
}