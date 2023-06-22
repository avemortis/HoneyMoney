package com.vtorushin.shared.setting.domain.usecases

import com.vtorushin.shared.setting.domain.repository.SettingsRepository

class GetPhoneNumberUseCase(private val repository: SettingsRepository) {
    operator fun invoke() = repository.getPhoneNumber()
}