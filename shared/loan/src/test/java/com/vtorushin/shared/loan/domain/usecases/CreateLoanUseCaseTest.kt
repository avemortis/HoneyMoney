package com.vtorushin.shared.loan.domain.usecases

import com.vtorushin.shared.loan.domain.entity.Loan
import com.vtorushin.shared.loan.domain.entity.LoanRequest
import com.vtorushin.shared.loan.domain.repository.LoanIssueRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.Test
import org.mockito.kotlin.eq
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

internal class CreateLoanUseCaseTest {
    private val loanIssueRepository: LoanIssueRepository = mock()
    private val case = CreateLoanUseCase(loanIssueRepository)
    private val loan: Loan = mock()
    private val request: LoanRequest = mock()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `invoke EXPECT loan`() = runTest {
        whenever(loanIssueRepository.requestLoan(eq(request))).thenReturn(loan)
        val actual = case(request)
        assertEquals(loan, actual)
    }
}