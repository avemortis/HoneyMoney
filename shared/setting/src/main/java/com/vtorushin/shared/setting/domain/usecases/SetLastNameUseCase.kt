package com.vtorushin.shared.setting.domain.usecases

import com.vtorushin.shared.setting.domain.repository.SettingsRepository

class SetLastNameUseCase(private val repository: SettingsRepository) {
    operator fun invoke(lastName: String) = repository.setLastName(lastName)
}