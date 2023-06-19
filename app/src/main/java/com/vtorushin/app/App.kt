package com.vtorushin.app

import android.app.Application
import androidx.savedstate.SavedStateRegistryOwner
import com.vtorushin.app.di.AppComponent
import com.vtorushin.app.di.DaggerAppComponent
import com.vtorushin.feature.registration.di.RegistrationComponent
import com.vtorushin.feature.registration.di.RegistrationComponentOwner
import com.vtorushin.feature.setting.di.SettingComponent
import com.vtorushin.feature.setting.di.SettingComponentOwner

class App : Application(), RegistrationComponentOwner, SettingComponentOwner {
    val appComponent: AppComponent = DaggerAppComponent.create()
    private var registrationComponent: RegistrationComponent? = null
    private var settingComponent: SettingComponent? = null

    override fun addRegisterComponent(savedStateRegistryOwner: SavedStateRegistryOwner): RegistrationComponent {
        if (registrationComponent == null) {
            registrationComponent = appComponent.registrationComponentFactory.create(
                savedStateRegistryOwner, this
            )
        }
        return registrationComponent!!
    }

    override fun clearRegisterComponent() {
        registrationComponent = null
    }

    override fun addSettingComponent(savedStateRegistryOwner: SavedStateRegistryOwner): SettingComponent {
        if (settingComponent == null) {
            settingComponent = appComponent.settingComponentFactory.create(
                savedStateRegistryOwner, this
            )
        }
        return settingComponent!!
    }

    override fun clearSettingComponent() {
        settingComponent = null
    }
}