package com.vtorushin.shared.auth.domain.usecases

import com.vtorushin.shared.auth.domain.entity.AuthBody
import com.vtorushin.shared.auth.domain.repository.AuthRepository

class RegisterUseCase(
    private val repository: AuthRepository
) {
    suspend fun invoke(authBody: AuthBody) = repository.register(authBody)
}