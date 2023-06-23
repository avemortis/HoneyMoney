package com.vtorushin.app.presentation.navigation

import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.vtorushin.component.tab.presentation.TabsScreenProvider
import com.vtorushin.feature.login.getLoginScreen
import com.vtorushin.feature.profile.getProfileScreen

class TabsScreenProvideImpl: TabsScreenProvider {
    override fun getFirstScreen(): FragmentScreen {
        return getProfileScreen()
    }

    override fun getSecondScreen(): FragmentScreen {
        return getLoginScreen()
    }
}