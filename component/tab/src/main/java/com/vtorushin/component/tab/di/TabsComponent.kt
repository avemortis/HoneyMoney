package com.vtorushin.component.tab.di

import androidx.fragment.app.Fragment
import androidx.savedstate.SavedStateRegistryOwner
import com.vtorushin.component.tab.presentation.TabsViewModel
import dagger.BindsInstance
import dagger.Subcomponent

@TabsScope
@Subcomponent(modules = [TabsModule::class])
interface TabsComponent {
    fun viewModel(): TabsViewModel

    @Subcomponent.Factory
    interface Factory {
        fun create(
            @BindsInstance savedStateRegistryOwner: SavedStateRegistryOwner
        ): TabsComponent
    }
}

internal fun Fragment.component() =
    (requireContext().applicationContext as TabsComponentOwner).addTabsComponent(this)

internal fun Fragment.clear() =
    (requireContext().applicationContext as TabsComponentOwner).clearTabsComponent()