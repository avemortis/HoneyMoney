package com.vtorushin.app.di

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.vtorushin.app.App
import com.vtorushin.app.ui.MainActivity
import com.vtorushin.feature.registration.di.RegistrationComponent
import com.vtorushin.feature.registration.di.RegistrationScope
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NavigationModule::class])
interface AppComponent {
    fun cicerone(): Cicerone<Router>
    fun router(): Router
    fun navigatorHolder(): NavigatorHolder
    @RegistrationScope
    val registrationComponentFactory: RegistrationComponent.Factory
}

fun MainActivity.component() = (application as App).appComponent