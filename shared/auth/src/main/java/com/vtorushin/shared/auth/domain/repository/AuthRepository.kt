package com.vtorushin.shared.auth.domain.repository

import com.vtorushin.shared.auth.domain.entity.AuthBody

interface AuthRepository {
    suspend fun generate(authBody: AuthBody): String
    fun get(): String?
    fun set(auth: String)
}