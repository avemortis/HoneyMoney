package com.vtorushin.app.presentation.navigation.main

import com.github.terrakok.cicerone.Router
import com.vtorushin.app.di.navigation.main.MainRouterLevel
import com.vtorushin.feature.profile.getProfileScreen
import com.vtorushin.feature.setting.presentation.SettingRouter

class SettingMainRouterImpl(@MainRouterLevel private val router: Router) : SettingRouter {
    override fun showProfile() {
        router.replaceScreen(getProfileScreen())
    }
}