package com.vtorushin.component.tab.presentation

import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

@Suppress("UNCHECKED_CAST")
class TabsViewModelFactory @AssistedInject constructor(
    @Assisted private val savedStateRegistryOwner: SavedStateRegistryOwner,
    @Assisted private val provider: TabsScreenProvider
) : AbstractSavedStateViewModelFactory(savedStateRegistryOwner, null) {
    override fun <T : ViewModel> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        return when (modelClass) {
            ContentTabViewModel::class.java ->
                ContentTabViewModel(
                    provider
                ) as T
            else -> throw IllegalStateException("Unknown viewModel")
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(
            savedStateRegistryOwner: SavedStateRegistryOwner,
            provider: TabsScreenProvider
        ): TabsViewModelFactory
    }
}