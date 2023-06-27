package com.vtorushin.shared.setting.domain.usecases

import com.vtorushin.shared.setting.domain.entity.LoginSecurityType
import com.vtorushin.shared.setting.domain.repository.TestSettingRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class SetLoginSecurityTypeUseCaseTest {
    private val repository = TestSettingRepository()
    private val useCase = SetLoginSecurityTypeUseCase(repository)

    private var type = LoginSecurityType.LOGIN_PASSWORD

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `invoke EXPECT type`() = runTest {
        val expectedType = type
        useCase(expectedType)
        assertEquals(expectedType, repository.getLoginSecurityType())
    }
}