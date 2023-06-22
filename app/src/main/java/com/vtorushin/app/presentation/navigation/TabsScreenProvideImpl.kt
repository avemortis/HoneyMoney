package com.vtorushin.app.presentation.navigation

import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.vtorushin.component.tab.presentation.TabsScreenProvider
import com.vtorushin.feature.login.getLoginScreen
import com.vtorushin.feature.registration.getRegistrationScreen

class TabsScreenProvideImpl: TabsScreenProvider {
    override fun getLoansScreen(): FragmentScreen {
        return getLoginScreen()
    }

    override fun getProfileScreen(): FragmentScreen {
        return getRegistrationScreen()
    }
}