package com.vtorushin.shared.setting.domain.usecases

import com.vtorushin.shared.setting.domain.repository.SettingsRepository

class SetNameUseCase(private val repository: SettingsRepository) {
    operator fun invoke(name: String) = repository.setName(name)
}