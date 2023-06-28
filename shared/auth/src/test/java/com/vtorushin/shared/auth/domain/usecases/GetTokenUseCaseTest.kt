package com.vtorushin.shared.auth.domain.usecases

import com.vtorushin.shared.auth.domain.repository.TokenRepository
import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

internal class GetTokenUseCaseTest {
    private val repository: TokenRepository = mock()
    private val case = GetTokenUseCase(repository)
    private val token = "Token"

    @Test
    fun `invoke EXPECT token`() {
        whenever(repository.get()).thenReturn(token)
        assertEquals(token, case())
    }
}