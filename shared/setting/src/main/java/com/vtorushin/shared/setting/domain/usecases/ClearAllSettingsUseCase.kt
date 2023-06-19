package com.vtorushin.shared.setting.domain.usecases

import com.vtorushin.shared.setting.domain.repository.SettingsRepository

class ClearAllSettingsUseCase(private val repository: SettingsRepository) {
    operator fun invoke() = repository.clearAllSettings()
}