package com.vtorushin.app.di

import android.content.Context
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.vtorushin.app.di.navigation.main.MainRouterLevel
import com.vtorushin.app.di.navigation.main.NavigationModule
import com.vtorushin.app.di.navigation.tabs.FirstTabNavigationModule
import com.vtorushin.app.di.navigation.tabs.SecondTabNavigationModule
import com.vtorushin.app.presentation.MainViewModel
import com.vtorushin.app.ui.MainActivity
import com.vtorushin.component.tab.di.TabsComponent
import com.vtorushin.component.tab.di.TabsScope
import com.vtorushin.feature.authoption.di.AuthOptionComponent
import com.vtorushin.feature.authoption.di.AuthOptionScope
import com.vtorushin.feature.loan.detail.di.LoanDetailComponent
import com.vtorushin.feature.loan.detail.di.LoanDetailScope
import com.vtorushin.feature.loan.di.LoanComponent
import com.vtorushin.feature.loan.di.LoanScope
import com.vtorushin.feature.loan.history.di.LoanHistoryComponent
import com.vtorushin.feature.loan.history.di.LoanHistoryScope
import com.vtorushin.feature.login.di.LoginComponent
import com.vtorushin.feature.login.di.LoginScope
import com.vtorushin.feature.profile.di.ProfileComponent
import com.vtorushin.feature.profile.di.ProfileScope
import com.vtorushin.feature.registration.di.RegistrationComponent
import com.vtorushin.feature.registration.di.RegistrationScope
import com.vtorushin.feature.setting.di.SettingComponent
import com.vtorushin.feature.setting.di.SettingScope
import com.vtorushin.features.loan.take.di.LoanTakeComponent
import com.vtorushin.features.loan.take.di.LoanTakeScope
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NavigationModule::class, FirstTabNavigationModule::class, SecondTabNavigationModule::class])
interface AppComponent {
    @MainRouterLevel
    fun cicerone(): Cicerone<Router>

    @MainRouterLevel
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

    @TabsScope
    val tabsComponentFactory: TabsComponent.Factory

    @ProfileScope
    val profileComponent: ProfileComponent.Factory

    @LoanScope
    val loanComponent: LoanComponent.Builder

    @LoanHistoryScope
    val loanHistoryComponent: LoanHistoryComponent.Factory

    @LoanDetailScope
    val loanDetailComponent: LoanDetailComponent.Factory

    @LoanTakeScope
    val loanTakeComponent: LoanTakeComponent.Factory

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance context: Context,
            @BindsInstance savedStateRegistryOwner: MainActivity
        ): AppComponent
    }
}

fun MainActivity.component() = (application as AppComponentOwner).addAppComponent(this)