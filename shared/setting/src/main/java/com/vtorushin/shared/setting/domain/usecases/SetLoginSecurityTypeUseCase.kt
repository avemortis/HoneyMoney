package com.vtorushin.shared.setting.domain.usecases

import com.vtorushin.shared.setting.domain.entity.LoginSecurityType
import com.vtorushin.shared.setting.domain.repository.SettingsRepository

class SetLoginSecurityTypeUseCase(private val repository: SettingsRepository) {
    operator fun invoke(securityType: LoginSecurityType) = repository.setLoginSecurityType(securityType)
}
