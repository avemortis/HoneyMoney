package com.vtorushin.feature.setting.presentation

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vtorushin.shared.setting.domain.entity.LoginSecurityType
import com.vtorushin.shared.setting.domain.usecases.*
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.onSubscription
import kotlinx.coroutines.launch
import javax.inject.Inject

class SettingViewModel @Inject constructor(
    private val setNameUseCase: SetNameUseCase,
    private val setLastNameUseCase: SetLastNameUseCase,
    private val setPhoneNumberUseCase: SetPhoneNumberUseCase,
    private val setLoginSecurityTypeUseCase: SetLoginSecurityTypeUseCase,
    getNameUseCase: GetNameUseCase,
    getLastNameUseCase: GetLastNameUseCase,
    getPhoneNumberUseCase: GetPhoneNumberUseCase,
    getLoginSecurityTypeUseCase: GetLoginSecurityTypeUseCase,
    private val clearAllSettingsUseCase: ClearAllSettingsUseCase,
    private val router: SettingRouter
) : ViewModel() {
    var name = getNameUseCase()
    var lastName = getLastNameUseCase()
    var phoneNumber = getPhoneNumberUseCase()
    var loginSecurityType = getLoginSecurityTypeUseCase()

    private val _errors = MutableSharedFlow<SettingUiState>()
    private val _state = MutableSharedFlow<SettingUiState>(replay = 1)
    val state = merge(_state, _errors)

    fun clear() = clearAllSettingsUseCase()

    fun saveSettings(fragmentManager: FragmentManager) {
        viewModelScope.launch {
            if (name.isBlank())
                _errors.emit(SettingUiState.NameIsEmpty)
            if (lastName.isBlank())
                _errors.emit(SettingUiState.LastNameIsEmpty)
            if (phoneNumber.isBlank())
                _errors.emit(SettingUiState.PhoneNumberIsEmpty)
            if (lastName.isNotBlank() && name.isNotBlank() && phoneNumber.isNotBlank()) {
                setNameUseCase(name)
                setLastNameUseCase(lastName)
                setPhoneNumberUseCase(phoneNumber)
                setLoginSecurityTypeUseCase(loginSecurityType)
                router.showProfile(fragmentManager)
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