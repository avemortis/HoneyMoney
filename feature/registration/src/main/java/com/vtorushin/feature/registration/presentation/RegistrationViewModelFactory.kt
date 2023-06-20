package com.vtorushin.feature.registration.presentation

import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.vtorushin.shared.auth.domain.repository.AuthRepository
import com.vtorushin.shared.auth.domain.repository.TokenRepository
import com.vtorushin.shared.auth.domain.usecases.LoginUseCase
import com.vtorushin.shared.auth.domain.usecases.RegisterUseCase
import com.vtorushin.shared.auth.domain.usecases.SetTokenUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

@Suppress("UNCHECKED_CAST")
class RegistrationViewModelFactory @AssistedInject constructor(
    @Assisted private val savedStateRegistryOwner: SavedStateRegistryOwner,
    @Assisted private val authRepository: AuthRepository,
    @Assisted private val tokenRepository: TokenRepository,
    @Assisted private val router: RegistrationRouter
) : AbstractSavedStateViewModelFactory(savedStateRegistryOwner, null) {
    override fun <T : ViewModel> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        return when (modelClass) {
            RegistrationViewModel::class.java ->
                RegistrationViewModel(
                    RegisterUseCase(authRepository),
                    LoginUseCase(authRepository),
                    SetTokenUseCase(tokenRepository),
                    router
                ) as T
            else -> throw IllegalStateException("Unknown viewModel")
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(
            savedStateRegistryOwner: SavedStateRegistryOwner,
            repository: AuthRepository,
            tokenRepository: TokenRepository,
            router: RegistrationRouter
        ): RegistrationViewModelFactory
    }
}