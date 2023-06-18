package com.vtorushin.shared.auth.domain.usecases

import com.vtorushin.shared.auth.domain.repository.AuthRepository

class GetTokenUseCase(
    private val repository: AuthRepository
) {
    operator fun invoke() = repository.get()
}