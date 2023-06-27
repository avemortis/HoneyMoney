package com.vtorushin.shared.setting.domain.repository

import com.vtorushin.shared.setting.domain.entity.LoginSecurityType

internal class TestSettingRepository : SettingsRepository {
    private var name = String()
    private var lastname = String()
    private var phoneNumber = String()
    private var loginSecurityType = LoginSecurityType.NO_SECURITY

    override fun setName(name: String) {
        this.name = name
    }

    override fun setLastName(lastname: String) {
        this.lastname = lastname
    }

    override fun setPhoneNumber(number: String) {
        this.phoneNumber = number
    }

    override fun setLoginSecurityType(loginSecurityType: LoginSecurityType) {
        this.loginSecurityType = loginSecurityType
    }

    override fun getName() = name

    override fun getLastName() = lastname

    override fun getPhoneNumber() = phoneNumber

    override fun getLoginSecurityType() = loginSecurityType

    override fun clearAllSettings() {
        name = String()
        lastname = String()
        phoneNumber = String()
        loginSecurityType = LoginSecurityType.NO_SECURITY
    }
}