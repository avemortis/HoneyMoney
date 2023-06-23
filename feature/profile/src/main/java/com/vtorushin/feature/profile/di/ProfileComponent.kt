package com.vtorushin.feature.profile.di

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.savedstate.SavedStateRegistryOwner
import com.vtorushin.feature.profile.presentation.ProfileViewModel
import dagger.BindsInstance
import dagger.Subcomponent

@ProfileScope
@Subcomponent(modules = [ProfileModule::class])
interface ProfileComponent {
    fun viewModel(): ProfileViewModel

    @Subcomponent.Factory
    interface Factory {
        fun create(
            @BindsInstance savedStateRegistryOwner: SavedStateRegistryOwner,
            @BindsInstance context: Context
        ): ProfileComponent
    }
}

internal fun Fragment.component() =
    (requireContext().applicationContext as ProfileComponentOwner).addProfileComponent(this)

internal fun Fragment.clear() =
    (requireContext().applicationContext as ProfileComponentOwner).clearProfileComponent()