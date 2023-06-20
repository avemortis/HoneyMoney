package com.vtorushin.shared.auth.data.repository

import com.vtorushin.shared.auth.data.remote.AuthApi
import com.vtorushin.shared.auth.domain.entity.AuthBody
import com.vtorushin.shared.auth.domain.entity.User
import com.vtorushin.shared.auth.domain.repository.AuthRepository
import javax.inject.Inject


class AuthRepositoryImpl @Inject constructor(
    private val authApi: AuthApi
) : AuthRepository {
    override suspend fun register(authBody: AuthBody): User = authApi.register(authBody)

    override suspend fun generateToken(authBody: AuthBody) = authApi.login(authBody)
}