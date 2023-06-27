package com.vtorushin.shared.setting.domain.usecases

import com.vtorushin.shared.setting.domain.repository.TestSettingRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class SetLastNameUseCaseTest {
    private val repository = TestSettingRepository()
    private val useCase = SetLastNameUseCase(repository)

    private var lastName = "Freeman"

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `invoke EXPECT last name`() = runTest {
        val expectedName = lastName
        useCase(expectedName)
        assertEquals(expectedName, repository.getLastName())
    }
}