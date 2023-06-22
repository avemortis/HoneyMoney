package com.vtorushin.shared.setting.data.repository

import android.content.Context
import com.vtorushin.shared.setting.domain.entity.LoginSecurityType
import com.vtorushin.shared.setting.domain.repository.SettingsRepository
import javax.inject.Inject

class SettingsRepositoryImpl @Inject constructor(
    private val context: Context
) : SettingsRepository {
    override fun setName(name: String) {
        val prefEdit = context.getSharedPreferences(PREF_KEY, Context.MODE_PRIVATE).edit()
        prefEdit.putString(NAME_KEY, name)
        prefEdit.apply()
    }

    override fun setLastName(lastname: String) {
        val prefEdit = context.getSharedPreferences(PREF_KEY, Context.MODE_PRIVATE).edit()
        prefEdit.putString(LAST_NAME_KEY, lastname)
        prefEdit.apply()
    }

    override fun setPhoneNumber(number: String) {
        val prefEdit = context.getSharedPreferences(PREF_KEY, Context.MODE_PRIVATE).edit()
        prefEdit.putString(PHONE_KEY, number)
        prefEdit.apply()
    }

    override fun setLoginSecurityType(loginSecurityType: LoginSecurityType) {
        val prefEdit = context.getSharedPreferences(PREF_KEY, Context.MODE_PRIVATE).edit()
        prefEdit.putString(SECURITY_SETTING, loginSecurityType.name)
        prefEdit.apply()
    }

    override fun getName(): String {
        val pref = context.getSharedPreferences(PREF_KEY, Context.MODE_PRIVATE)
        return pref.getString(NAME_KEY, null)?: String()
    }

    override fun getLastName(): String {
        val pref = context.getSharedPreferences(PREF_KEY, Context.MODE_PRIVATE)
        return pref.getString(LAST_NAME_KEY, null)?: String()
    }

    override fun getPhoneNumber(): String {
        val pref = context.getSharedPreferences(PREF_KEY, Context.MODE_PRIVATE)
        return pref.getString(PHONE_KEY, null)?: String()
    }

    override fun getLoginSecurityType(): LoginSecurityType {
        val pref = context.getSharedPreferences(PREF_KEY, Context.MODE_PRIVATE)
        return LoginSecurityType.valueOf(
            pref.getString(SECURITY_SETTING, null) ?: LoginSecurityType.LOGIN_PASSWORD.name
        )
    }

    override fun clearAllSettings() {
        val pref = context.getSharedPreferences(PREF_KEY, Context.MODE_PRIVATE)
        pref.edit().apply {
            remove(NAME_KEY)
            remove(LAST_NAME_KEY)
            remove(SECURITY_SETTING)
        }.apply()
    }

    companion object {
        private const val PREF_KEY = "SharedPref Key"
        private const val NAME_KEY = "Name Key"
        private const val LAST_NAME_KEY = "Last Name Key"
        private const val PHONE_KEY = "Phone Key"
        private const val SECURITY_SETTING = "Security Setting"
    }
}