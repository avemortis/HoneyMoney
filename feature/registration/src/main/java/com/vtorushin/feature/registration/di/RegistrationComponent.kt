package com.vtorushin.feature.registration.di

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.savedstate.SavedStateRegistryOwner
import com.vtorushin.feature.registration.presentation.RegistrationViewModel
import dagger.BindsInstance
import dagger.Subcomponent

@RegistrationScope
@Subcomponent(modules = [RegistrationModule::class])
interface RegistrationComponent {
    fun viewModel(): RegistrationViewModel

    @Subcomponent.Factory
    interface Factory {
        fun create(
            @BindsInstance savedStateRegistryOwner: SavedStateRegistryOwner,
            @BindsInstance context: Context
        ): RegistrationComponent
    }
}

internal fun Fragment.component() =
    (requireContext().applicationContext as RegistrationComponentOwner)
        .addRegisterComponent(this)

internal fun Fragment.clearComponent() =
    (requireContext().applicationContext as RegistrationComponentOwner)
        .clearRegisterComponent()
