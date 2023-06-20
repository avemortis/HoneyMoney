package com.vtorushin.feature.authoption.presentation

import androidx.lifecycle.ViewModel
import javax.inject.Inject

class AuthOptionViewModel @Inject constructor(
    private val router: AuthOptionRouter
) : ViewModel() {
    fun toLogin() = router.toLogin()
    fun toRegistration() = router.toRegistration()
}