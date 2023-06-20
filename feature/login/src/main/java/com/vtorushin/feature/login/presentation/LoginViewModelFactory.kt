package com.vtorushin.feature.login.presentation

import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.vtorushin.shared.auth.domain.repository.AuthRepository
import com.vtorushin.shared.auth.domain.repository.TokenRepository
import com.vtorushin.shared.auth.domain.usecases.LoginUseCase
import com.vtorushin.shared.auth.domain.usecases.SetTokenUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

@Suppress("UNCHECKED_CAST")
class LoginViewModelFactory @AssistedInject constructor(
    @Assisted private val savedStateRegistryOwner: SavedStateRegistryOwner,
    @Assisted private val authRepository: AuthRepository,
    @Assisted private val tokenRepository: TokenRepository,
    @Assisted private val router: LoginRouter
) : AbstractSavedStateViewModelFactory(savedStateRegistryOwner, null) {
    override fun <T : ViewModel> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        return when (modelClass) {
            LoginViewModel::class.java ->
                LoginViewModel(
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
            authRepository: AuthRepository,
            tokenRepository: TokenRepository,
            router: LoginRouter
        ): LoginViewModelFactory
    }
}