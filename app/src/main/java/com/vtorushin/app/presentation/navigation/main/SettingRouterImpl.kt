package com.vtorushin.app.presentation.navigation.main

import com.github.terrakok.cicerone.Router
import com.vtorushin.feature.profile.getProfileScreen
import com.vtorushin.feature.setting.presentation.SettingRouter

class SettingRouterImpl(private val router: Router) : SettingRouter {
    override fun showProfile() {
        router.replaceScreen(getProfileScreen())
    }
}