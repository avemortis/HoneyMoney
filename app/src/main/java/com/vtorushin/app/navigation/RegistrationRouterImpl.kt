package com.vtorushin.app.navigation

import com.github.terrakok.cicerone.Router
import com.vtorushin.feature.profile.getProfileScreen
import com.vtorushin.feature.registration.presentation.RegistrationRouter

class RegistrationRouterImpl(private val router: Router): RegistrationRouter {
    override fun editProfile() {
        router.navigateTo(getProfileScreen())
    }
}