package com.vtorushin.shared.setting.domain.usecases

import com.vtorushin.shared.setting.domain.repository.TestSettingRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class SetNameUseCaseTest {
    private val repository = TestSettingRepository()
    private val useCase = SetNameUseCase(repository)

    private var name = "Gordon"

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `invoke EXPECT name`() = runTest {
        val expectedName = name
        useCase(expectedName)
        assertEquals(expectedName, repository.getName())
    }
}