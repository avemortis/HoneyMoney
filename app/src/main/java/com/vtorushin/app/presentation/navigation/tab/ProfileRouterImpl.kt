package com.vtorushin.app.presentation.navigation.tab

import androidx.fragment.app.FragmentManager
import com.github.terrakok.cicerone.Router
import com.vtorushin.feature.onboarding.ui.OnBoardingHostFragment
import com.vtorushin.feature.profile.presentation.ProfileRouter
import javax.inject.Inject

class ProfileRouterImpl : ProfileRouter {
    @Inject
    override fun showOnBoarding(fragmentManager: FragmentManager) {
        OnBoardingHostFragment().show(fragmentManager, OnBoardingHostFragment.TAG)
    }
}