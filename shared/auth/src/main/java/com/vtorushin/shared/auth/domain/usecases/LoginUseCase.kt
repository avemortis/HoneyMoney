package com.vtorushin.shared.auth.domain.usecases

import com.vtorushin.shared.auth.domain.entity.AuthBody
import com.vtorushin.shared.auth.domain.repository.AuthRepository

class LoginUseCase(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(authBody: AuthBody) = repository.generateToken(authBody)
}