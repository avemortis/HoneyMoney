package com.vtorushin.shared.loan.domain.repository

import com.vtorushin.shared.loan.domain.entity.Loan

interface LoanStorageRepository {
    suspend fun insertLoan(loan: Loan)
}