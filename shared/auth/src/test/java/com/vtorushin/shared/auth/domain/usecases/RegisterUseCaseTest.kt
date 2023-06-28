package com.vtorushin.shared.auth.domain.usecases

import com.vtorushin.shared.auth.domain.entity.AuthBody
import com.vtorushin.shared.auth.domain.entity.User
import com.vtorushin.shared.auth.domain.repository.AuthRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.Test
import org.mockito.kotlin.eq
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

internal class RegisterUseCaseTest {
    private val repository: AuthRepository = mock()
    private val case = RegisterUseCase(repository)
    private val authBody: AuthBody = mock()
    private val user: User = mock()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `invoke EXPECT user`() = runTest {
        whenever(repository.register(eq(authBody))).thenReturn(user)
        val actual = case(authBody)
        assertEquals(user, actual)
    }
}