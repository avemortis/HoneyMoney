package com.vtorushin.feature.registration.presentation

sealed interface RegistrationUiState {
    object Init : RegistrationUiState
    object Successes : RegistrationUiState
    data class Error(val error: RegistrationError) : RegistrationUiState
}

enum class RegistrationError {
    USER_ALREADY_EXIST,
    NETWORK_ERROR
}