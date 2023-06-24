package com.vtorushin.feature.loan.history.presentation

interface LoanHistoryRouter {
    fun overviewLoan(loanId: Int)
    fun takeNewLoan()
}