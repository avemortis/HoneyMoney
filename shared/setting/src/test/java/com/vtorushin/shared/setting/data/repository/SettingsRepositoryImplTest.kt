package com.vtorushin.shared.setting.data.repository

import android.content.Context
import android.content.SharedPreferences
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.vtorushin.shared.setting.domain.entity.LoginSecurityType
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.*
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@RunWith(AndroidJUnit4::class)
@ExtendWith(MockitoExtension::class)
internal class SettingsRepositoryImplTest {
    private lateinit var context: Context
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var sharedPrefEditor: SharedPreferences.Editor
    private val repository by lazy { SettingsRepositoryImpl(context) }

    private val name = "Saul"
    private val lastName = "Goodman"
    private val phoneNumber = "505-503-4455"
    private val login = LoginSecurityType.NO_SECURITY

    @BeforeEach
    fun setup() {
        sharedPreferences = mock()
        context = mock()
        sharedPrefEditor = mock()
        whenever(context.getSharedPreferences(anyString(), anyInt())).thenReturn(sharedPreferences)
    }

    private fun setupForEdit() {
        whenever(sharedPreferences.edit()).thenReturn(sharedPrefEditor)
        whenever(sharedPrefEditor.putString(anyString(), anyString())).thenReturn(sharedPrefEditor)
    }

    @Test
    fun `setName EXPECT does not throw`() {
        setupForEdit()
        assertDoesNotThrow { repository.setName(name) }
    }

    @Test
    fun `setLastName EXPECT does not throw`() {
        setupForEdit()
        assertDoesNotThrow { repository.setLastName(lastName) }
    }

    @Test
    fun `setPhoneNumber EXPECT does not throw`() {
        setupForEdit()
        assertDoesNotThrow { repository.setPhoneNumber(phoneNumber) }
    }

    @Test
    fun `setLoginSecurityType EXPECT does not throw`() {
        setupForEdit()
        assertDoesNotThrow { repository.setLoginSecurityType(login) }
    }

    @Test
    fun `getName EXPECT name`() {
        whenever(sharedPreferences.getString(anyString(), any())).thenReturn(name)
        assertEquals(name, repository.getName())
    }

    @Test
    fun `getLastName EXPECT last name`() {
        whenever(sharedPreferences.getString(anyString(), any())).thenReturn(lastName)
        assertEquals(lastName, repository.getLastName())
    }

    @Test
    fun `getPhoneNumber EXPECT phone number`() {
        whenever(sharedPreferences.getString(anyString(), any())).thenReturn(phoneNumber)
        assertEquals(phoneNumber, repository.getPhoneNumber())
    }

    @Test
    fun `getLoginSecurityType EXPECT login`() {
        whenever(sharedPreferences.getString(anyString(), any())).thenReturn(login.name)
        assertEquals(login, repository.getLoginSecurityType())
    }

    @Test
    fun `clearAllSettings EXPECT does not throw`() {
        whenever(sharedPreferences.edit()).thenReturn(sharedPrefEditor)
        whenever(sharedPrefEditor.remove(anyString())).thenReturn(sharedPrefEditor)
        assertDoesNotThrow { repository.clearAllSettings() }
    }
}