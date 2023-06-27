package com.vtorushin.feature.login.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vtorushin.shared.auth.domain.entity.AuthBody
import com.vtorushin.shared.auth.domain.usecases.LoginUseCase
import com.vtorushin.shared.auth.domain.usecases.SetTokenUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException

class LoginViewModel(
    private val loginUseCase: LoginUseCase,
    private val setTokenUseCase: SetTokenUseCase,
    private val registrationRouter: LoginRouter
) : ViewModel() {
    var login = String()
    var password = String()

    private val _errors = MutableSharedFlow<LoginUiState>()
    private val _state = MutableSharedFlow<LoginUiState>(replay = 1)
    val state = _state.asSharedFlow()

    fun register() {
        viewModelScope.launch {
            if (login.isBlank())
                _errors.emit(LoginUiState.Error(LoginError.LOGIN_EMPTY))
            if (password.isBlank())
                _errors.emit(LoginUiState.Error(LoginError.PASSWORD_EMPTY))
            if (password.isNotBlank() && login.isNotBlank()) {
                try {
                    val auth = AuthBody(login, password)
                    val token = loginUseCase(auth)
                    setTokenUseCase(token)
                    _state.emit(LoginUiState.Successes)
                    registrationRouter.toProfile()
                } catch (e: HttpException) {
                    _errors.emit(LoginUiState.Error(LoginError.USER_NOT_FOUND))
                } catch (e: Exception) {
                    _state.emit(LoginUiState.Error(LoginError.NETWORK_ERROR))
                }
            }
        }
    }
}