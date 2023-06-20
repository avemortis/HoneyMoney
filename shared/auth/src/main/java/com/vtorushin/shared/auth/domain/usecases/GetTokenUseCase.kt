package com.vtorushin.shared.auth.domain.usecases

import com.vtorushin.shared.auth.domain.repository.TokenRepository

class GetTokenUseCase(
    private val repository: TokenRepository
) {
    operator fun invoke() = repository.get()
}