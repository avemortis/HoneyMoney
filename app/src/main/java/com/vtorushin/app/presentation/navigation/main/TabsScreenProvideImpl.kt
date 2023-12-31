package com.vtorushin.app.presentation.navigation.main

import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.vtorushin.component.tab.presentation.TabsScreenProvider
import com.vtorushin.feature.loan.history.getLoanHistoryScreen
import com.vtorushin.feature.login.getLoginScreen
import com.vtorushin.feature.profile.getProfileScreen
import com.vtorushin.feature.registration.getRegistrationScreen

class TabsScreenProvideImpl: TabsScreenProvider {
    override fun getFirstScreen(): FragmentScreen {
        return getProfileScreen()
    }

    override fun getSecondScreen(): FragmentScreen {
        return getLoanHistoryScreen()
    }
}