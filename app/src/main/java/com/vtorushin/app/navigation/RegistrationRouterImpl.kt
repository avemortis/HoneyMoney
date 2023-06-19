package com.vtorushin.app.navigation

import com.github.terrakok.cicerone.Router
import com.vtorushin.feature.registration.presentation.RegistrationRouter
import com.vtorushin.feature.setting.getSettingScreen
import com.vtorushin.feature.setting.ui.SettingLaunchMode

class RegistrationRouterImpl(private val router: Router) : RegistrationRouter {
    override fun editProfile() {
        router.replaceScreen(getSettingScreen(SettingLaunchMode.WITH_SETTINGS_CLEARING))
    }
}