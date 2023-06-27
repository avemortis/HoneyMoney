package com.vtorushin.shared.setting.domain.usecases

import com.vtorushin.shared.setting.domain.repository.TestSettingRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class GetPhoneNumberUseCaseTest {
    private val repository = TestSettingRepository()
    private val useCase = GetPhoneNumberUseCase(repository)

    private val phone = "911"


    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `invoke EXPECT phone`() = runTest {
        repository.setPhoneNumber(phone)
        val expectedPhone = phone
        val actualPhone = useCase()
        assertEquals(expectedPhone, actualPhone)
    }
}