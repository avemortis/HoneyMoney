package com.vtorushin.app.di.navigation.main

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.vtorushin.app.presentation.navigation.main.*
import com.vtorushin.component.tab.presentation.TabsScreenProvider
import com.vtorushin.feature.authoption.presentation.AuthOptionRouter
import com.vtorushin.feature.login.presentation.LoginRouter
import com.vtorushin.feature.registration.presentation.RegistrationRouter
import com.vtorushin.feature.setting.presentation.SettingRouter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NavigationModule {
    @Provides
    @Singleton
    fun provideRegistrationRouter(@MainRouterLevel router: Router): RegistrationRouter =
        RegistrationRouterImpl(router)

    @Provides
    @Singleton
    fun provideSettingRouter(@MainRouterLevel router: Router): SettingRouter =
        SettingMainRouterImpl(router)

    @Provides
    @Singleton
    fun provideLoginRouter(@MainRouterLevel router: Router): LoginRouter = LoginRouterImpl(router)

    @Provides
    @Singleton
    fun provideAuthOptionRouter(@MainRouterLevel router: Router): AuthOptionRouter =
        AuthOptionRouterImpl(router)

    @Provides
    @Singleton
    @MainRouterLevel
    fun provideCicerone(): Cicerone<Router> = Cicerone.create()

    @Provides
    @Singleton
    @MainRouterLevel
    fun provideRouter(@MainRouterLevel cicerone: Cicerone<Router>): Router = cicerone.router

    @Provides
    @Singleton
    fun provideNavigatorHolder(@MainRouterLevel cicerone: Cicerone<Router>): NavigatorHolder =
        cicerone.getNavigatorHolder()

    @Provides
    @Singleton
    fun provideTabsScreenProvider(): TabsScreenProvider =
        TabsScreenProvideImpl()
}