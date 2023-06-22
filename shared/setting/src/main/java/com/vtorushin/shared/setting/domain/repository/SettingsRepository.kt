package com.vtorushin.shared.setting.domain.repository

import com.vtorushin.shared.setting.domain.entity.LoginSecurityType

interface SettingsRepository {
    fun setName(name: String)
    fun setLastName(lastname: String)
    fun setPhoneNumber(number: String)
    fun setLoginSecurityType(loginSecurityType: LoginSecurityType)
    fun getName(): String
    fun getLastName(): String
    fun getPhoneNumber(): String
    fun getLoginSecurityType(): LoginSecurityType
    fun clearAllSettings()
}