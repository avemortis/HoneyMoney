package com.vtorushin.feature.login.di

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.savedstate.SavedStateRegistryOwner
import com.vtorushin.feature.login.presentation.LoginViewModel
import dagger.BindsInstance
import dagger.Subcomponent

@LoginScope
@Subcomponent(modules = [LoginModule::class])
interface LoginComponent {
    fun viewModel(): LoginViewModel

    @Subcomponent.Factory
    interface Factory {
        fun create(
            @BindsInstance savedStateRegistryOwner: SavedStateRegistryOwner,
            @BindsInstance context: Context
        ): LoginComponent
    }
}

internal fun Fragment.component() =
    (requireContext().applicationContext as LoginComponentOwner).addLoginComponent(this)

internal fun Fragment.clear() =
    (requireContext().applicationContext as LoginComponentOwner).clearLoginComponent()