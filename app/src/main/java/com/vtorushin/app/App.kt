package com.vtorushin.app

import android.app.Application
import android.content.Context
import androidx.savedstate.SavedStateRegistryOwner
import com.vtorushin.app.di.AppComponent
import com.vtorushin.app.di.DaggerAppComponent
import com.vtorushin.feature.registration.di.RegistrationComponent
import com.vtorushin.feature.registration.di.RegistrationComponentOwner

class App : Application(), RegistrationComponentOwner {
    val appComponent: AppComponent = DaggerAppComponent.create()
    private var registrationComponent: RegistrationComponent? = null

    override fun addComponent(
        savedStateRegistryOwner: SavedStateRegistryOwner,
        context: Context
    ): RegistrationComponent {
        if (registrationComponent == null) {
            registrationComponent = appComponent.registrationComponentFactory.create(
                savedStateRegistryOwner, context
            )
        }
        return registrationComponent!!
    }

    override fun clear() {
        registrationComponent = null
    }
}