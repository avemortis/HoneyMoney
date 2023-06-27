package com.vtorushin.shared.setting.domain.usecases

import com.vtorushin.shared.setting.domain.repository.TestSettingRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


internal class GetLastNameUseCaseTest {
    private val repository = TestSettingRepository()
    private val useCase = GetLastNameUseCase(repository)

    private val lastName = "Freeman"

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `invoke EXPECT last name`() = runTest {
        repository.setLastName(lastName)
        val expectedLastName = lastName

        val actualLastName = useCase()
        assertEquals(expectedLastName, actualLastName)
    }
}