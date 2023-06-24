package com.vtorushin.app.presentation.navigation.tab

import com.github.terrakok.cicerone.Router
import com.vtorushin.component.tab.di.SecondTab
import com.vtorushin.feature.loan.detail.presentation.LoanDetailRouter

class LoanDetailRouterImpl(@SecondTab private val router: Router) : LoanDetailRouter {
    override fun backToHistory() {
        router.exit()
    }
}