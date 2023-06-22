package com.vtorushin.shared.loan.domain.repository

import com.vtorushin.shared.loan.domain.entity.Loan

interface LoanRepository {
    suspend fun getLoans(): List<Loan>
    suspend fun getLoan(id: Int): Loan
}