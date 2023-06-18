package com.vtorushin.shared.auth.domain.usecases

import com.vtorushin.shared.auth.domain.repository.AuthRepository

class SetTokenUseCase(
    private val repository: AuthRepository
) {
    operator fun invoke(auth: String) = repository.set(auth)
}