package com.vtorushin.app.presentation

import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.vtorushin.shared.auth.domain.repository.AuthRepository
import com.vtorushin.shared.auth.domain.repository.TokenRepository
import com.vtorushin.shared.auth.domain.usecases.GetTokenUseCase
import com.vtorushin.shared.auth.domain.usecases.LoginUseCase
import com.vtorushin.shared.auth.domain.usecases.RegisterUseCase
import com.vtorushin.shared.auth.domain.usecases.SetTokenUseCase
import com.vtorushin.shared.setting.domain.repository.SettingsRepository
import com.vtorushin.shared.setting.domain.usecases.GetLastNameUseCase
import com.vtorushin.shared.setting.domain.usecases.GetLoginSecurityTypeUseCase
import com.vtorushin.shared.setting.domain.usecases.GetNameUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory @AssistedInject constructor(
    @Assisted private val savedStateRegistryOwner: SavedStateRegistryOwner,
    @Assisted private val settingsRepository: SettingsRepository,
    @Assisted private val tokenRepository: TokenRepository,
) : AbstractSavedStateViewModelFactory(savedStateRegistryOwner, null) {
    override fun <T : ViewModel> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        return when (modelClass) {
            MainViewModel::class.java ->
                MainViewModel(
                    GetNameUseCase(settingsRepository),
                    GetLastNameUseCase(settingsRepository),
                    GetLoginSecurityTypeUseCase(settingsRepository),
                    GetTokenUseCase(tokenRepository)
                ) as T
            else -> throw IllegalStateException("Unknown viewModel")
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(
            savedStateRegistryOwner: SavedStateRegistryOwner,
            settingRepository: SettingsRepository,
            tokenRepository: TokenRepository
        ): MainViewModelFactory
    }
}
