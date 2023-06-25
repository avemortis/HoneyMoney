package com.vtorushin.app

import android.app.Application
import androidx.savedstate.SavedStateRegistryOwner
import com.vtorushin.app.di.AppComponent
import com.vtorushin.app.di.AppComponentOwner
import com.vtorushin.app.di.DaggerAppComponent
import com.vtorushin.app.ui.MainActivity
import com.vtorushin.component.tab.di.TabsComponent
import com.vtorushin.component.tab.di.TabsComponentOwner
import com.vtorushin.feature.authoption.di.AuthOptionComponent
import com.vtorushin.feature.authoption.di.AuthOptionComponentOwner
import com.vtorushin.feature.loan.detail.di.LoanDetailComponent
import com.vtorushin.feature.loan.detail.di.LoanDetailComponentOwner
import com.vtorushin.feature.loan.history.di.LoanHistoryComponent
import com.vtorushin.feature.loan.history.di.LoanHistoryComponentOwner
import com.vtorushin.feature.login.di.LoginComponent
import com.vtorushin.feature.login.di.LoginComponentOwner
import com.vtorushin.feature.profile.di.ProfileComponent
import com.vtorushin.feature.profile.di.ProfileComponentOwner
import com.vtorushin.feature.registration.di.RegistrationComponent
import com.vtorushin.feature.registration.di.RegistrationComponentOwner
import com.vtorushin.feature.setting.di.SettingComponent
import com.vtorushin.feature.setting.di.SettingComponentOwner
import com.vtorushin.features.loan.take.di.LoanTakeComponent
import com.vtorushin.features.loan.take.di.LoanTakeComponentOwner
import java.lang.RuntimeException

class App : Application(), AppComponentOwner, RegistrationComponentOwner, SettingComponentOwner,
    LoginComponentOwner, AuthOptionComponentOwner, TabsComponentOwner, ProfileComponentOwner,
    LoanHistoryComponentOwner, LoanDetailComponentOwner, LoanTakeComponentOwner {
    private var appComponent: AppComponent? = null
    private var registrationComponent: RegistrationComponent? = null
    private var settingComponent: SettingComponent? = null
    private var loginComponent: LoginComponent? = null
    private var authOptionComponent: AuthOptionComponent? = null
    private var tabsComponent: TabsComponent? = null
    private var profileComponent: ProfileComponent? = null
    private val loanComponent by lazy {
        appComponent?.loanComponent?.build() ?: throw RuntimeException("Illegal component state")
    }
    private var loanHistoryComponent: LoanHistoryComponent? = null
    private var loanDetailComponent: LoanDetailComponent? = null
    private var loanTakeComponent: LoanTakeComponent? = null

    override fun addAppComponent(activity: MainActivity): AppComponent {
        if (appComponent == null)
            appComponent = DaggerAppComponent.factory().create(this, activity)

        return appComponent!!
    }

    override fun addRegisterComponent(savedStateRegistryOwner: SavedStateRegistryOwner): RegistrationComponent {
        appComponent?.let {
            if (registrationComponent == null) {
                registrationComponent = it.registrationComponentFactory.create(
                    savedStateRegistryOwner, this
                )
            }
        }

        return registrationComponent!!
    }

    override fun clearRegisterComponent() {
        registrationComponent = null
    }

    override fun addSettingComponent(savedStateRegistryOwner: SavedStateRegistryOwner): SettingComponent {
        appComponent?.let {
            if (settingComponent == null) {
                settingComponent = it.settingComponentFactory.create(
                    savedStateRegistryOwner, this
                )
            }
        }

        return settingComponent!!
    }

    override fun clearSettingComponent() {
        settingComponent = null
    }

    override fun addLoginComponent(savedStateRegistryOwner: SavedStateRegistryOwner): LoginComponent {
        appComponent?.let {
            if (loginComponent == null) {
                loginComponent = it.loginComponentFactory.create(
                    savedStateRegistryOwner,
                    this
                )
            }
        }

        return loginComponent!!
    }

    override fun clearLoginComponent() {
        loginComponent = null
    }

    override fun addAuthOptionComponent(savedStateRegistryOwner: SavedStateRegistryOwner): AuthOptionComponent {
        appComponent?.let {
            if (authOptionComponent == null) {
                authOptionComponent = it.authOptionComponentFactory.create(
                    savedStateRegistryOwner,
                    this
                )
            }
        }

        return authOptionComponent!!
    }

    override fun clearAuthOptionComponent() {
        authOptionComponent = null
    }

    override fun addTabsComponent(savedStateRegistryOwner: SavedStateRegistryOwner): TabsComponent {
        appComponent?.let {
            if (tabsComponent == null) {
                tabsComponent = it.tabsComponentFactory.create(
                    savedStateRegistryOwner
                )
            }
        }

        return tabsComponent!!
    }

    override fun clearTabsComponent() {
        tabsComponent = null
    }

    override fun addProfileComponent(savedStateRegistryOwner: SavedStateRegistryOwner): ProfileComponent {
        appComponent?.let {
            if (profileComponent == null) {
                profileComponent = it.profileComponent.create(
                    savedStateRegistryOwner,
                    this
                )
            }
        }

        return profileComponent!!
    }

    override fun clearProfileComponent() {
        profileComponent = null
    }

    override fun addLoanHistoryComponent(savedStateRegistryOwner: SavedStateRegistryOwner): LoanHistoryComponent {
        appComponent?.let {
            if (loanHistoryComponent == null) {
                loanHistoryComponent = it.loanHistoryComponent.create(
                    savedStateRegistryOwner,
                    loanComponent.provideLoanRepository(),
                    this
                )
            }
        }

        return loanHistoryComponent!!
    }

    override fun clearLoanHistoryComponent() {
        loanHistoryComponent = null
    }

    override fun addLoanDetailComponent(savedStateRegistryOwner: SavedStateRegistryOwner): LoanDetailComponent {
        appComponent?.let {
            if (loanDetailComponent == null) {
                loanDetailComponent = it.loanDetailComponent.create(
                    savedStateRegistryOwner,
                    loanComponent.provideLoanRepository(),
                    this
                )
            }
        }

        return loanDetailComponent!!
    }

    override fun clearLoanDetailComponent() {
        loanDetailComponent = null
    }

    override fun addLoanTakeComponent(savedStateRegistryOwner: SavedStateRegistryOwner): LoanTakeComponent {
        appComponent?.let {
            if (loanTakeComponent == null) {
                loanTakeComponent = it.loanTakeComponent.create(
                    savedStateRegistryOwner,
                    loanComponent.provideLoanConditionRepository(),
                    loanComponent.provideLoanIssueRepository()
                )
            }
        }

        return loanTakeComponent!!
    }

    override fun clearLoanTakeComponent() {
        loanDetailComponent = null
    }
}