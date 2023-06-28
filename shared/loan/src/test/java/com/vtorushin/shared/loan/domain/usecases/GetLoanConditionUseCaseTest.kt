package com.vtorushin.shared.loan.domain.usecases

import com.vtorushin.shared.loan.domain.entity.LoanCondition
import com.vtorushin.shared.loan.domain.repository.LoanConditionRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

internal class GetLoanConditionUseCaseTest {
    private val loanConditionRepository: LoanConditionRepository = mock()
    private val case = GetLoanConditionUseCase(loanConditionRepository)
    private val loanCondition: LoanCondition = mock()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `invoke EXPECT loanCondition`() = runTest {
        whenever(loanConditionRepository.getLoanCondition()).thenReturn(loanCondition)
        val actual = case()
        assertEquals(loanCondition, actual)
    }
}