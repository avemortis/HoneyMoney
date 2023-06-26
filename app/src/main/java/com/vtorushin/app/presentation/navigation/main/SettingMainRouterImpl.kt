package com.vtorushin.app.presentation.navigation.main

import androidx.fragment.app.FragmentManager
import com.github.terrakok.cicerone.Router
import com.vtorushin.app.di.navigation.main.MainRouterLevel
import com.vtorushin.component.tab.getTabScreen
import com.vtorushin.feature.onboarding.ui.OnBoardingHostFragment
import com.vtorushin.feature.setting.presentation.SettingRouter

class SettingMainRouterImpl(
    @MainRouterLevel private val router: Router
) : SettingRouter {
    override fun showProfile(fragmentManager: FragmentManager) {
        router.replaceScreen(getTabScreen())
        OnBoardingHostFragment().show(fragmentManager, OnBoardingHostFragment.TAG)
    }
}