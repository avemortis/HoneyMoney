package com.vtorushin.feature.profile.presentation

import androidx.lifecycle.ViewModel
import com.vtorushin.shared.setting.domain.usecases.GetLastNameUseCase
import com.vtorushin.shared.setting.domain.usecases.GetNameUseCase
import com.vtorushin.shared.setting.domain.usecases.GetPhoneNumberUseCase
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    private val getNameUseCase: GetNameUseCase,
    private val getLastNameUseCase: GetLastNameUseCase,
    private val getPhoneNumberUseCase: GetPhoneNumberUseCase,
    private val router: ProfileRouter
) : ViewModel() {
    fun edit() {
        router.editProfile()
    }
}