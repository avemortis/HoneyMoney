package com.vtorushin.feature.setting.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vtorushin.shared.setting.domain.entity.LoginSecurityType
import com.vtorushin.shared.setting.domain.usecases.*
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.onSubscription
import kotlinx.coroutines.launch
import javax.inject.Inject

class SettingViewModel @Inject constructor(
    private val setNameUseCase: SetNameUseCase,
    private val setLastNameUseCase: SetLastNameUseCase,
    private val setLoginSecurityTypeUseCase: SetLoginSecurityTypeUseCase,
    getNameUseCase: GetNameUseCase,
    getLastNameUseCase: GetLastNameUseCase,
    getLoginSecurityTypeUseCase: GetLoginSecurityTypeUseCase,
    private val clearAllSettingsUseCase: ClearAllSettingsUseCase,
    private val router: SettingRouter
) : ViewModel() {
    var name = getNameUseCase()
    var lastName = getLastNameUseCase()
    var loginSecurityType = getLoginSecurityTypeUseCase()

    private val _state = MutableSharedFlow<SettingUiState>(replay = 1).apply {
        onSubscription {
            emit(
                SettingUiState.SettingsState(
                    name, lastName, loginSecurityType == LoginSecurityType.LOGIN_PASSWORD
                )
            )
        }
    }
    val state = _state.asSharedFlow()

    fun clear() = clearAllSettingsUseCase()

    fun saveSettings() {
        viewModelScope.launch {
            if (name.isBlank())
                _state.emit(SettingUiState.NameIsEmpty)
            if (lastName.isBlank())
                _state.emit(SettingUiState.LastNameIsEmpty)
            if (lastName.isNotBlank() && name.isNotBlank()) {
                setNameUseCase(name)
                setLastNameUseCase(lastName)
                setLoginSecurityTypeUseCase(loginSecurityType)
                router.showProfile()
            }
        }
    }

    fun onRememberMeChanged(to: Boolean) {
        loginSecurityType = when (to) {
            true -> LoginSecurityType.NO_SECURITY
            false -> LoginSecurityType.LOGIN_PASSWORD
        }
    }
}