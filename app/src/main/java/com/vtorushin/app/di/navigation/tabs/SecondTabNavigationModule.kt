package com.vtorushin.app.di.navigation.tabs

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import com.vtorushin.app.presentation.navigation.tab.LoanDetailRouterImpl
import com.vtorushin.app.presentation.navigation.tab.LoanHistoryRouterImpl
import com.vtorushin.app.presentation.navigation.tab.LoanTakeRouterImpl
import com.vtorushin.app.presentation.navigation.tab.ProfileRouterImpl
import com.vtorushin.component.tab.di.FirstTab
import com.vtorushin.component.tab.di.SecondTab
import com.vtorushin.feature.loan.detail.presentation.LoanDetailRouter
import com.vtorushin.feature.loan.history.presentation.LoanHistoryRouter
import com.vtorushin.feature.profile.presentation.ProfileRouter
import com.vtorushin.features.loan.take.presentation.LoanTakeRouter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class SecondTabNavigationModule {
    @Provides
    @Singleton
    fun provideLoanHistoryRouter(@SecondTab router: Router) : LoanHistoryRouter = LoanHistoryRouterImpl(router)

    @Provides
    @Singleton
    fun provideLoanDetailRouter(@SecondTab router: Router) : LoanDetailRouter = LoanDetailRouterImpl(router)

    @Provides
    @Singleton
    fun provideLoanTakeRouter(@SecondTab router: Router) : LoanTakeRouter = LoanTakeRouterImpl(router)

    @Provides
    @SecondTab
    @Singleton
    fun provideSecondCicerone() : Cicerone<Router> = Cicerone.create()

    @Provides
    @SecondTab
    @Singleton
    fun provideSecondRouter(@SecondTab cicerone: Cicerone<Router>) : Router = cicerone.router
}