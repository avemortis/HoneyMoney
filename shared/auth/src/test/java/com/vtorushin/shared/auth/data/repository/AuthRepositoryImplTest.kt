package com.vtorushin.shared.auth.data.repository

import com.vtorushin.shared.auth.data.remote.AuthApi
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

@OptIn(ExperimentalCoroutinesApi::class)
internal class AuthRepositoryImplTest {
    private val authApi: AuthApi = mock()
    private val repository = AuthRepositoryImpl(authApi)
    private val authBody: AuthBody = mock()


    @Test
    fun `register EXPECT user`() = runTest {
        val user: User = mock()
        whenever(authApi.register(eq(authBody))).thenReturn(user)
        val actual = repository.register(authBody)
        assertEquals(user, actual)
    }

    @Test
    fun `generateToken EXPECT token`() = runTest {
        val token = "Token"
        whenever(authApi.login(eq(authBody))).thenReturn(token)
        val actual = repository.generateToken(authBody)
        assertEquals(token, actual)
    }
}