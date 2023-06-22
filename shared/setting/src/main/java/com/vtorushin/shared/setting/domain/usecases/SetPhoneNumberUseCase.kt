package com.vtorushin.shared.setting.domain.usecases

import com.vtorushin.shared.setting.domain.repository.SettingsRepository

class SetPhoneNumberUseCase(private val repository: SettingsRepository) {
    operator fun invoke(number: String) = repository.setPhoneNumber(number)
}