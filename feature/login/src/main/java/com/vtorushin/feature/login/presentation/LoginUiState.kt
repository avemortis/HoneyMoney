package com.vtorushin.feature.login.presentation

sealed interface LoginUiState {
    object Successes : LoginUiState
    data class State(val login: String, val password: String) : LoginUiState
    data class Error(val error: LoginError) : LoginUiState
}

enum class LoginError {
    LOGIN_EMPTY,
    PASSWORD_EMPTY,
    USER_NOT_FOUND,
    NETWORK_ERROR
}