package com.vtorushin.shared.auth.domain.usecases

import com.vtorushin.shared.auth.domain.repository.TokenRepository

class SetTokenUseCase(
    private val repository: TokenRepository
) {
    operator fun invoke(auth: String) = repository.set(auth)
}