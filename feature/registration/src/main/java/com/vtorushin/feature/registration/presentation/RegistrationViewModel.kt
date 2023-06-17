package com.vtorushin.feature.registration.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vtorushin.shared.auth.domain.entity.AuthBody
import com.vtorushin.shared.auth.domain.usecases.RegisterUseCase
import kotlinx.coroutines.launch

class RegistrationViewModel(
    private val registerUseCase: RegisterUseCase
) : ViewModel() {
    fun register(login: String, password: String) {
        viewModelScope.launch {
            registerUseCase.invoke(
                AuthBody(login, password)
            )
        }
    }
}