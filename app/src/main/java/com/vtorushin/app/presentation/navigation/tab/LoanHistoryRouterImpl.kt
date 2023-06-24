package com.vtorushin.app.presentation.navigation.tab

import com.github.terrakok.cicerone.Router
import com.vtorushin.component.tab.di.SecondTab
import com.vtorushin.feature.loan.history.presentation.LoanHistoryRouter
import com.vtorushin.feature.login.getLoginScreen
import com.vtorushin.shared.loan.domain.entity.Loan

class LoanHistoryRouterImpl(@SecondTab private val router: Router) : LoanHistoryRouter {
    override fun overviewLoan(loanId: Int) {
        router.navigateTo(getLoginScreen())
    }

    override fun takeNewLoan() {
        router.navigateTo(getLoginScreen())
    }
}