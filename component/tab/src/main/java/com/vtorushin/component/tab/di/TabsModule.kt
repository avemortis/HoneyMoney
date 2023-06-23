package com.vtorushin.component.tab.di

import androidx.savedstate.SavedStateRegistryOwner
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import com.vtorushin.component.tab.presentation.TabsScreenProvider
import com.vtorushin.component.tab.presentation.ContentTabViewModel
import com.vtorushin.component.tab.presentation.TabsViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class TabsModule {
    @Provides
    @TabsScope
    fun provideViewModel(
        savedStateRegistryOwner: SavedStateRegistryOwner,
        provider: TabsScreenProvider
    ): ContentTabViewModel {
        return TabsViewModelFactory(
            savedStateRegistryOwner,
            provider
        ).create(ContentTabViewModel::class.java)
    }

}