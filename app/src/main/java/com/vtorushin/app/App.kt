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
import com.vtorushin.feature.login.di.LoginComponent
import com.vtorushin.feature.login.di.LoginComponentOwner
import com.vtorushin.feature.registration.di.RegistrationComponent
import com.vtorushin.feature.registration.di.RegistrationComponentOwner
import com.vtorushin.feature.setting.di.SettingComponent
import com.vtorushin.feature.setting.di.SettingComponentOwner

class App : Application(), AppComponentOwner, RegistrationComponentOwner, SettingComponentOwner,
    LoginComponentOwner, AuthOptionComponentOwner, TabsComponentOwner {
    private var appComponent: AppComponent? = null
    private var registrationComponent: RegistrationComponent? = null
    private var settingComponent: SettingComponent? = null
    private var loginComponent: LoginComponent? = null
    private var authOptionComponent: AuthOptionComponent? = null
    private var tabsComponent: TabsComponent? = null

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
}