package com.vtorushin.app.di

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.vtorushin.app.presentation.navigation.AuthOptionRouterImpl
import com.vtorushin.app.presentation.navigation.LoginRouterImpl
import com.vtorushin.app.presentation.navigation.RegistrationRouterImpl
import com.vtorushin.app.presentation.navigation.SettingRouterImpl
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
    fun provideRegistrationRouter(router: Router): RegistrationRouter = RegistrationRouterImpl(router)

    @Provides
    @Singleton
    fun provideSettingRouter(router: Router): SettingRouter = SettingRouterImpl(router)

    @Provides
    @Singleton
    fun provideLoginRouter(router: Router): LoginRouter = LoginRouterImpl(router)

    @Provides
    @Singleton
    fun provideAuthOptionRouter(router: Router): AuthOptionRouter = AuthOptionRouterImpl(router)

    @Provides
    @Singleton
    fun provideCicerone(): Cicerone<Router> = Cicerone.create()

    @Provides
    @Singleton
    fun provideRouter(cicerone: Cicerone<Router>): Router = cicerone.router

    @Provides
    @Singleton
    fun provideNavigatorHolder(cicerone: Cicerone<Router>): NavigatorHolder =
        cicerone.getNavigatorHolder()
}