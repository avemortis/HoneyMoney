package com.vtorushin.component.tab.di

import androidx.fragment.app.Fragment
import androidx.savedstate.SavedStateRegistryOwner
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.vtorushin.component.tab.presentation.ContentTabViewModel
import dagger.BindsInstance
import dagger.Subcomponent

@TabsScope
@Subcomponent(modules = [TabsModule::class])
interface TabsComponent {
    fun viewModel(): ContentTabViewModel
    @FirstTab
    fun firstCicerone(): Cicerone<Router>
    @SecondTab
    fun secondCicerone(): Cicerone<Router>
    @FirstTab
    fun firstRouter(): Router
    @SecondTab
    fun secondRouter(): Router

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