package com.vtorushin.shared.setting.domain.usecases

import com.vtorushin.shared.setting.domain.entity.LoginSecurityType
import com.vtorushin.shared.setting.domain.repository.TestSettingRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.kotlin.inOrder

internal class ClearAllSettingsUseCaseTest {
    private val repository = TestSettingRepository()
    private val useCase = ClearAllSettingsUseCase(repository)

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `invoke EXPECT empty fields`() = runTest {
        repository.setName("test")
        repository.setLastName("test")
        repository.setPhoneNumber("test")
        repository.setLoginSecurityType(LoginSecurityType.LOGIN_PASSWORD)
        useCase()
        assertEquals("", repository.getName())
        assertEquals("", repository.getLastName())
        assertEquals("", repository.getPhoneNumber())
        assertEquals(LoginSecurityType.NO_SECURITY, repository.getLoginSecurityType())
    }
}