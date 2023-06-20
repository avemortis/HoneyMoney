package com.vtorushin.app.presentation

import androidx.lifecycle.ViewModel
import com.github.terrakok.cicerone.Screen
import com.vtorushin.feature.authoption.getAuthOptionScreen
import com.vtorushin.feature.profile.getProfileScreen
import com.vtorushin.feature.setting.getSettingScreen
import com.vtorushin.feature.setting.ui.SettingLaunchMode
import com.vtorushin.shared.auth.domain.usecases.GetTokenUseCase
import com.vtorushin.shared.setting.domain.entity.LoginSecurityType
import com.vtorushin.shared.setting.domain.usecases.GetLastNameUseCase
import com.vtorushin.shared.setting.domain.usecases.GetLoginSecurityTypeUseCase
import com.vtorushin.shared.setting.domain.usecases.GetNameUseCase
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getNameUseCase: GetNameUseCase,
    private val getLastNameUseCase: GetLastNameUseCase,
    private val getLoginSecurityTypeUseCase: GetLoginSecurityTypeUseCase,
    private val getTokenUseCase: GetTokenUseCase
) : ViewModel() {
    fun getStartScreen() : Screen {
        val token = getTokenUseCase()
        val name = getNameUseCase()
        val lastName = getLastNameUseCase()
        val loginSecurityType = getLoginSecurityTypeUseCase()
        return when {
            token.isNullOrEmpty() -> getAuthOptionScreen()
            name.isBlank() || lastName.isBlank() -> getSettingScreen(SettingLaunchMode.WITHOUT_SETTINGS_CLEARING)
            loginSecurityType == LoginSecurityType.LOGIN_PASSWORD -> getAuthOptionScreen()
            loginSecurityType == LoginSecurityType.NO_SECURITY -> getProfileScreen()
            else -> throw IllegalStateException("Wrong start screen state")
        }
    }
}