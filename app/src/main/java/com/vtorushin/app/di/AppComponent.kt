package com.vtorushin.app.di

import android.content.Context
import androidx.savedstate.SavedStateRegistryOwner
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.vtorushin.app.App
import com.vtorushin.app.presentation.MainViewModel
import com.vtorushin.app.ui.MainActivity
import com.vtorushin.feature.authoption.di.AuthOptionComponent
import com.vtorushin.feature.authoption.di.AuthOptionScope
import com.vtorushin.feature.login.di.LoginComponent
import com.vtorushin.feature.login.di.LoginScope
import com.vtorushin.feature.registration.di.RegistrationComponent
import com.vtorushin.feature.registration.di.RegistrationScope
import com.vtorushin.feature.setting.di.SettingComponent
import com.vtorushin.feature.setting.di.SettingScope
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NavigationModule::class])
interface AppComponent {
    fun cicerone(): Cicerone<Router>
    fun router(): Router
    fun navigatorHolder(): NavigatorHolder
    fun viewModel(): MainViewModel
    @RegistrationScope
    val registrationComponentFactory: RegistrationComponent.Factory
    @SettingScope
    val settingComponentFactory: SettingComponent.Factory
    @LoginScope
    val loginComponentFactory: LoginComponent.Factory
    @AuthOptionScope
    val authOptionComponentFactory: AuthOptionComponent.Factory

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance context: Context,
            @BindsInstance savedStateRegistryOwner: MainActivity
        ) : AppComponent
    }
}

fun MainActivity.component() = (application as AppComponentOwner).addAppComponent(this)