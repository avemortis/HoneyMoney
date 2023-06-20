package com.vtorushin.feature.authoption.di

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.savedstate.SavedStateRegistryOwner
import com.vtorushin.feature.authoption.presentation.AuthOptionViewModel
import dagger.BindsInstance
import dagger.Subcomponent

@AuthOptionScope
@Subcomponent(modules = [AuthOptionModule::class])
interface AuthOptionComponent {
    fun viewModel(): AuthOptionViewModel

    @Subcomponent.Factory
    interface Factory {
        fun create(
            @BindsInstance savedStateRegistryOwner: SavedStateRegistryOwner,
            @BindsInstance context: Context
        ): AuthOptionComponent
    }
}

internal fun Fragment.component() =
    (requireContext().applicationContext as AuthOptionComponentOwner).addAuthOptionComponent(this)

internal fun Fragment.clear() =
    (requireContext().applicationContext as AuthOptionComponentOwner).clearAuthOptionComponent()