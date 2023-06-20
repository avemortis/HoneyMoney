package com.vtorushin.app.presentation.navigation

import com.github.terrakok.cicerone.Router
import com.vtorushin.feature.login.presentation.LoginRouter
import com.vtorushin.feature.profile.getProfileScreen

class LoginRouterImpl(private val router: Router) : LoginRouter {
    override fun toProfile() {
        router.newRootScreen(getProfileScreen())
    }
}