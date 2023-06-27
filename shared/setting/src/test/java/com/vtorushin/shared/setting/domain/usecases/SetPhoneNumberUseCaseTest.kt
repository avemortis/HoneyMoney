package com.vtorushin.shared.setting.domain.usecases

import com.vtorushin.shared.setting.domain.repository.TestSettingRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class SetPhoneNumberUseCaseTest {
    private val repository = TestSettingRepository()
    private val useCase = SetPhoneNumberUseCase(repository)

    private var number = "911"

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `invoke EXPECT number`() = runTest {
        val expectedNumber = number
        useCase(expectedNumber)
        assertEquals(expectedNumber, repository.getPhoneNumber())
    }
}