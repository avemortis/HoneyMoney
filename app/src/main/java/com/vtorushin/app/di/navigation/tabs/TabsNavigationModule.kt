package com.vtorushin.app.di.navigation.tabs

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import com.vtorushin.app.presentation.navigation.tab.LoanHistoryRouterImpl
import com.vtorushin.app.presentation.navigation.tab.ProfileRouterImpl
import com.vtorushin.component.tab.di.FirstTab
import com.vtorushin.component.tab.di.SecondTab
import com.vtorushin.feature.loan.history.presentation.LoanHistoryRouter
import com.vtorushin.feature.profile.presentation.ProfileRouter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class TabsNavigationModule {
    @Provides
    @Singleton
    fun provideProfileRouter(@FirstTab router: Router) : ProfileRouter = ProfileRouterImpl(router)

    @Provides
    @Singleton
    fun provideLoanHistoryRouter(@SecondTab router: Router) : LoanHistoryRouter = LoanHistoryRouterImpl(router)

    @Provides
    @FirstTab
    @Singleton
    fun provideFirstCicerone() : Cicerone<Router> = Cicerone.create()

    @Provides
    @FirstTab
    @Singleton
    fun provideFirstRouter(@FirstTab cicerone: Cicerone<Router>) : Router = cicerone.router

    @Provides
    @SecondTab
    @Singleton
    fun provideSecondCicerone() : Cicerone<Router> = Cicerone.create()

    @Provides
    @SecondTab
    @Singleton
    fun provideSecondRouter(@SecondTab cicerone: Cicerone<Router>) : Router = cicerone.router
}