package com.vtorushin.feature.profile.presentation

import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.vtorushin.shared.setting.domain.repository.SettingsRepository
import com.vtorushin.shared.setting.domain.usecases.*
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

@Suppress("UNCHECKED_CAST")
class ProfileViewModelFactory @AssistedInject constructor(
    @Assisted private val savedStateRegistryOwner: SavedStateRegistryOwner,
    @Assisted private val settingsRepository: SettingsRepository,
    @Assisted private val router: ProfileRouter
) : AbstractSavedStateViewModelFactory(savedStateRegistryOwner, null) {
    override fun <T : ViewModel> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        return when (modelClass) {
            ProfileViewModel::class.java ->
                ProfileViewModel(
                    GetNameUseCase(settingsRepository),
                    GetLastNameUseCase(settingsRepository),
                    GetPhoneNumberUseCase(settingsRepository),
                    router
                ) as T
            else -> throw IllegalStateException("Unknown viewModel")
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(
            savedStateRegistryOwner: SavedStateRegistryOwner,
            repository: SettingsRepository,
            router: ProfileRouter
        ): ProfileViewModelFactory
    }
}