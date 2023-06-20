package com.vtorushin.shared.auth.data.repository

import android.content.Context
import com.vtorushin.shared.auth.domain.repository.TokenRepository

class TokenRepositoryImpl(private val context: Context) : TokenRepository {
    override fun get(): String? {
        val pref = context.getSharedPreferences(AUTH, Context.MODE_PRIVATE)
        return pref.getString(AUTH_KEY, null)
    }

    override fun set(auth: String) {
        val prefEdit = context.getSharedPreferences(AUTH, Context.MODE_PRIVATE).edit()
        prefEdit.putString(AUTH_KEY, auth)
        prefEdit.apply()
    }

    companion object {
        private const val AUTH = "auth"
        private const val AUTH_KEY = "Auth Key"
    }
}