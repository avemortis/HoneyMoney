package com.vtorushin.shared.setting.domain.usecases

import com.vtorushin.shared.setting.domain.repository.TestSettingRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class GetNameUseCaseTest {
    private val repository = TestSettingRepository()
    private val useCase = GetNameUseCase(repository)

    private val name = "Gordon"

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `invoke EXPECT name`() = runTest {
        repository.setName(name)
        val expectedName = name
        val actualName = useCase()
        assertEquals(expectedName, actualName)
    }
}