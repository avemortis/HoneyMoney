package com.vtorushin.feature.registration.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vtorushin.shared.auth.domain.entity.AuthBody
import com.vtorushin.shared.auth.domain.usecases.LoginUseCase
import com.vtorushin.shared.auth.domain.usecases.RegisterUseCase
import com.vtorushin.shared.auth.domain.usecases.SetTokenUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.net.ConnectException

class RegistrationViewModel(
    private val registerUseCase: RegisterUseCase,
    private val loginUseCase: LoginUseCase,
    private val setTokenUseCase: SetTokenUseCase,
    private val registrationRouter: RegistrationRouter
) : ViewModel() {
    var login = String()
    var password = String()

    private val _state: MutableSharedFlow<RegistrationUiState> =
        MutableSharedFlow<RegistrationUiState>(
            replay = 0
        ).apply {
            onSubscription {
                emit(RegistrationUiState.State(login, password))
            }
        }
    val state = _state.asSharedFlow()

    fun register() {
        viewModelScope.launch {
            if (login.isBlank())
                _state.emit(RegistrationUiState.Error(RegistrationError.LOGIN_EMPTY))
            if (password.isBlank())
                _state.emit(RegistrationUiState.Error(RegistrationError.PASSWORD_EMPTY))
            if (password.isNotBlank() && login.isNotBlank()) {
                try {
                    val auth = AuthBody(login, password)
                    registerUseCase(auth)
                    val token = loginUseCase(auth)
                    setTokenUseCase(token)
                    _state.emit(RegistrationUiState.Successes)
                    registrationRouter.editProfile()
                } catch (e: HttpException) {
                    _state.emit(RegistrationUiState.Error(RegistrationError.USER_ALREADY_EXIST))
                } catch (e: Exception) {
                    _state.emit(RegistrationUiState.Error(RegistrationError.NETWORK_ERROR))
                }
            }
        }
    }
}