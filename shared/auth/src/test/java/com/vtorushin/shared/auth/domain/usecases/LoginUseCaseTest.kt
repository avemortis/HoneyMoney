package com.vtorushin.shared.auth.domain.usecases

import com.vtorushin.shared.auth.domain.entity.AuthBody
import com.vtorushin.shared.auth.domain.repository.AuthRepository
import com.vtorushin.shared.auth.domain.repository.TokenRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.Test
import org.mockito.kotlin.eq
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

internal class LoginUseCaseTest {
    private val repository: AuthRepository = mock()
    private val case = LoginUseCase(repository)
    private val authBody: AuthBody = mock()
    private val token = "Token"

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `invoke EXPECT token`() = runTest {
        whenever(repository.generateToken(eq(authBody))).thenReturn(token)
        val actual = case(authBody)
        assertEquals(token, actual)
    }
}