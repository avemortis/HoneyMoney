package com.vtorushin.feature.setting.di

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.savedstate.SavedStateRegistryOwner
import com.vtorushin.feature.setting.presentation.SettingViewModel
import com.vtorushin.feature.setting.ui.SettingLaunchMode
import dagger.BindsInstance
import dagger.Subcomponent

@SettingScope
@Subcomponent(modules = [SettingModule::class])
interface SettingComponent {
    fun viewModel(): SettingViewModel

    @Subcomponent.Factory
    interface Factory {
        fun create(
            @BindsInstance savedStateRegistryOwner: SavedStateRegistryOwner,
            @BindsInstance context: Context
        ): SettingComponent
    }
}

internal fun Fragment.component() =
    (requireContext().applicationContext as SettingComponentOwner)
        .addSettingComponent(this)

internal fun Fragment.clearComponent() =
    (requireContext().applicationContext as SettingComponentOwner)
        .clearSettingComponent()