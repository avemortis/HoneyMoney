package com.vtorushin.feature.loan.history.presentation

import com.vtorushin.shared.loan.domain.entity.Loan

interface LoanHistoryRouter {
    fun overviewLoan(loan: Loan)
    fun takeNewLoan()
}