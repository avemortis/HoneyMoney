package com.vtorushin.feature.profile.presentation

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import com.vtorushin.shared.setting.domain.usecases.GetLastNameUseCase
import com.vtorushin.shared.setting.domain.usecases.GetNameUseCase
import com.vtorushin.shared.setting.domain.usecases.GetPhoneNumberUseCase
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    getNameUseCase: GetNameUseCase,
    getLastNameUseCase: GetLastNameUseCase,
    getPhoneNumberUseCase: GetPhoneNumberUseCase,
    private val router: ProfileRouter
) : ViewModel() {
    val name = getNameUseCase()
    val lastName = getLastNameUseCase()
    val phone = getPhoneNumberUseCase()

    fun onboarding(fragmentManager: FragmentManager) {
        router.showOnBoarding(fragmentManager)
    }
}