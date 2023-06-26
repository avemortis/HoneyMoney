package com.vtorushin.app.presentation.navigation.tab

import androidx.fragment.app.FragmentManager
import com.github.terrakok.cicerone.Router
import com.vtorushin.feature.onboarding.ui.OnBoardingHostFragment
import com.vtorushin.feature.profile.presentation.ProfileRouter

class ProfileRouterImpl(private val fragmentManager: FragmentManager) : ProfileRouter {
    override fun showOnBoarding() {
        OnBoardingHostFragment().show(fragmentManager, OnBoardingHostFragment.TAG)
    }
}