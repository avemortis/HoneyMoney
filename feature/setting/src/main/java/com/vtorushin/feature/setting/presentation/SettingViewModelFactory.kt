package com.vtorushin.feature.setting.presentation

import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.vtorushin.feature.setting.ui.SettingLaunchMode
import com.vtorushin.shared.setting.domain.repository.SettingsRepository
import com.vtorushin.shared.setting.domain.usecases.*
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

@Suppress("UNCHECKED_CAST")
class SettingViewModelFactory @AssistedInject constructor(
    @Assisted private val savedStateRegistryOwner: SavedStateRegistryOwner,
    @Assisted private val settingsRepository: SettingsRepository
) : AbstractSavedStateViewModelFactory(savedStateRegistryOwner, null) {
    override fun <T : ViewModel> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        return when (modelClass) {
            SettingViewModel::class.java ->
                SettingViewModel(
                    SetNameUseCase(settingsRepository),
                    SetLastNameUseCase(settingsRepository),
                    SetLoginSecurityTypeUseCase(settingsRepository),
                    GetNameUseCase(settingsRepository),
                    GetLastNameUseCase(settingsRepository),
                    GetLoginSecurityTypeUseCase(settingsRepository),
                    ClearAllSettingsUseCase(settingsRepository)
                ) as T
            else -> throw IllegalStateException("Unknown viewModel")
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(
            savedStateRegistryOwner: SavedStateRegistryOwner,
            repository: SettingsRepository
        ): SettingViewModelFactory
    }
}