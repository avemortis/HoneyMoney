package com.vtorushin.shared.auth.domain.repository

import com.vtorushin.shared.auth.domain.entity.AuthBody
import com.vtorushin.shared.auth.domain.entity.User

interface AuthRepository {
    suspend fun register(authBody: AuthBody): User
    suspend fun generateToken(authBody: AuthBody): String
}