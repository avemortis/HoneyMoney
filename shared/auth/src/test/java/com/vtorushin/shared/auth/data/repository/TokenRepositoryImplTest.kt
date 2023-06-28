package com.vtorushin.shared.auth.data.repository

import android.content.Context
import android.content.SharedPreferences
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach

import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.*
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

internal class TokenRepositoryImplTest {
    private val context: Context = mock()
    private val sharedPreferences: SharedPreferences = mock()

    private val repository by lazy { TokenRepositoryImpl(context) }

    @BeforeEach
    fun setup() {
        whenever(context.getSharedPreferences(anyString(), anyInt())).thenReturn(sharedPreferences)
    }

    @Test
    fun `get EXPECT token`() {
        val token = "Token"
        whenever(sharedPreferences.getString(anyString(), any())).thenReturn(token)
        val actual = repository.get()
        assertEquals(token, actual)
    }

    @Test
    fun `set EXPECT does not throw`() {
        val sharedPrefEditor: SharedPreferences.Editor = mock()
        whenever(sharedPreferences.edit()).thenReturn(sharedPrefEditor)
        whenever(sharedPrefEditor.putString(anyString(), anyString())).thenReturn(sharedPrefEditor)
        assertDoesNotThrow { repository.set("Auth") }
    }
}