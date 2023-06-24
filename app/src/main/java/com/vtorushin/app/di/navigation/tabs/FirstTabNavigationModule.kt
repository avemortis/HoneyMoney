package com.vtorushin.app.di.navigation.tabs

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import com.vtorushin.app.presentation.navigation.tab.ProfileRouterImpl
import com.vtorushin.component.tab.di.FirstTab
import com.vtorushin.feature.profile.presentation.ProfileRouter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class FirstTabNavigationModule {
    @Provides
    @Singleton
    fun provideProfileRouter(@FirstTab router: Router) : ProfileRouter = ProfileRouterImpl(router)

    @Provides
    @FirstTab
    @Singleton
    fun provideFirstCicerone() : Cicerone<Router> = Cicerone.create()

    @Provides
    @FirstTab
    @Singleton
    fun provideFirstRouter(@FirstTab cicerone: Cicerone<Router>) : Router = cicerone.router
}