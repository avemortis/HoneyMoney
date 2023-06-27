package com.vtorushin.app.presentation.navigation.tab

import androidx.fragment.app.FragmentManager
import com.vtorushin.feature.onboarding.ui.OnboardingHostFragment
import com.vtorushin.feature.profile.presentation.ProfileRouter
import javax.inject.Inject

class ProfileRouterImpl : ProfileRouter {
    @Inject
    override fun showOnBoarding(fragmentManager: FragmentManager) {
        OnboardingHostFragment().show(fragmentManager, OnboardingHostFragment.TAG)
    }
}