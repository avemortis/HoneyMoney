package com.vtorushin.feature.registration.presentation

sealed interface RegistrationUiState {
    object Successes : RegistrationUiState
    data class State(val login: String, val password: String) : RegistrationUiState
    data class Error(val error: RegistrationError) : RegistrationUiState
}

enum class RegistrationError {
    LOGIN_EMPTY,
    PASSWORD_EMPTY,
    USER_ALREADY_EXIST,
    NETWORK_ERROR
}