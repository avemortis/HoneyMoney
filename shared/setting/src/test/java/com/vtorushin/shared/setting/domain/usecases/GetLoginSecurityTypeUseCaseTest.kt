package com.vtorushin.shared.setting.domain.usecases

import com.vtorushin.shared.setting.domain.entity.LoginSecurityType
import com.vtorushin.shared.setting.domain.repository.SettingsRepository
import com.vtorushin.shared.setting.domain.repository.TestSettingRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
internal class GetLoginSecurityTypeUseCaseTest {
    private val repository = TestSettingRepository()
    private val useCase = GetLoginSecurityTypeUseCase(repository)

    private val type = LoginSecurityType.LOGIN_PASSWORD

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `invoke EXPECT type`() = runTest {
        repository.setLoginSecurityType(type)
        val expectedType = type

        val actualType = useCase()
        assertEquals(expectedType, actualType)
    }
}