package com.vtorushin.app.presentation.navigation.tab

import com.github.terrakok.cicerone.Router
import com.vtorushin.component.tab.di.SecondTab
import com.vtorushin.features.loan.take.presentation.LoanTakeRouter

class LoanTakeRouterImpl(@SecondTab private val router: Router) : LoanTakeRouter {
    override fun backToHistory() {
        router.exit()
    }
}