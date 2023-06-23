package com.vtorushin.app.presentation.navigation.tab

import android.util.Log
import com.github.terrakok.cicerone.Router
import com.vtorushin.feature.profile.presentation.ProfileRouter
import com.vtorushin.feature.setting.getSettingScreen
import com.vtorushin.feature.setting.ui.SettingLaunchMode

class ProfileRouterImpl(private val router: Router) : ProfileRouter {
    override fun editProfile() {
        router.navigateTo(getSettingScreen(SettingLaunchMode.WITHOUT_SETTINGS_CLEARING))
    }

    init {
        Log.d("Router" ,"from impl $router")
    }
}