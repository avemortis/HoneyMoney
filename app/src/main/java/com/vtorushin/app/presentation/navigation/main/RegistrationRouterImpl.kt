package com.vtorushin.app.presentation.navigation.main

import com.github.terrakok.cicerone.Router
import com.vtorushin.feature.registration.presentation.RegistrationRouter
import com.vtorushin.feature.setting.getSettingScreen
import com.vtorushin.feature.setting.ui.SettingLaunchMode

class RegistrationRouterImpl(private val router: Router) : RegistrationRouter {
    override fun editProfile() {
        router.newRootScreen(getSettingScreen(SettingLaunchMode.WITH_SETTINGS_CLEARING))
    }
}