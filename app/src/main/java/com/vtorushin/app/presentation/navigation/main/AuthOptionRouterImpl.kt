package com.vtorushin.app.presentation.navigation.main

import com.github.terrakok.cicerone.Router
import com.vtorushin.feature.authoption.presentation.AuthOptionRouter
import com.vtorushin.feature.login.getLoginScreen
import com.vtorushin.feature.registration.getRegistrationScreen

class AuthOptionRouterImpl(private val router: Router) : AuthOptionRouter {
    override fun toLogin() {
        router.navigateTo(getLoginScreen())
    }

    override fun toRegistration() {
        router.navigateTo(getRegistrationScreen())
    }
}