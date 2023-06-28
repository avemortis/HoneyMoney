package com.vtorushin.shared.loan.domain.usecases

import com.vtorushin.shared.loan.domain.entity.Loan
import com.vtorushin.shared.loan.domain.repository.LoanRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

internal class GetLoanUseCaseTest {
    private val loanRepository: LoanRepository = mock()
    private val case = GetLoanUseCase(loanRepository)
    private val loans: Loan = mock()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `invoke EXPECT loan`() = runTest {
        whenever(loanRepository.getLoan(anyInt())).thenReturn(loans)
        assertEquals(loans, case(5))
    }
}