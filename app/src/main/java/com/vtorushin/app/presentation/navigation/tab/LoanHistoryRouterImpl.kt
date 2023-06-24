package com.vtorushin.app.presentation.navigation.tab

import com.github.terrakok.cicerone.Router
import com.vtorushin.component.tab.di.SecondTab
import com.vtorushin.feature.loan.detail.getLoanDetailScreen
import com.vtorushin.feature.loan.history.presentation.LoanHistoryRouter
import com.vtorushin.feature.login.getLoginScreen

class LoanHistoryRouterImpl(@SecondTab private val router: Router) : LoanHistoryRouter {
    override fun overviewLoan(loanId: Int) {
        router.navigateTo(getLoanDetailScreen(loanId))
    }

    override fun takeNewLoan() {
        router.navigateTo(getLoginScreen())
    }
}