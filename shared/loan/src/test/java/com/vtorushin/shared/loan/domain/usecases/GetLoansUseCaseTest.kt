package com.vtorushin.shared.loan.domain.usecases

import com.vtorushin.shared.loan.domain.entity.Loan
import com.vtorushin.shared.loan.domain.entity.LoanRequest
import com.vtorushin.shared.loan.domain.repository.LoanIssueRepository
import com.vtorushin.shared.loan.domain.repository.LoanRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

internal class GetLoansUseCaseTest {
    private val loanRepository: LoanRepository = mock()
    private val case = GetLoansUseCase(loanRepository)
    private val loans: List<Loan> = mock()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `invoke EXPECT loans`() = runTest {
        whenever(loanRepository.getLoans()).thenReturn(loans)
        assertEquals(loans, case())
    }
}