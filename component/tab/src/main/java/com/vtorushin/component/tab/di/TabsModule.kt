package com.vtorushin.component.tab.di

import androidx.savedstate.SavedStateRegistryOwner
import com.vtorushin.component.tab.presentation.TabsScreenProvider
import com.vtorushin.component.tab.presentation.TabsViewModel
import com.vtorushin.component.tab.presentation.TabsViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class TabsModule {
    @Provides
    @TabsScope
    fun provideViewModel(
        savedStateRegistryOwner: SavedStateRegistryOwner,
        provider: TabsScreenProvider
    ): TabsViewModel {
        return TabsViewModelFactory(
            savedStateRegistryOwner,
            provider
        ).create(TabsViewModel::class.java)
    }
}