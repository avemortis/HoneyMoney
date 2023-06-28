package com.vtorushin.shared.loan.data.repository

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.vtorushin.shared.loan.data.remote.api.LoanApi
import com.vtorushin.shared.loan.data.remote.dtos.LoanDto
import com.vtorushin.shared.loan.data.remote.dtos.toDto
import com.vtorushin.shared.loan.domain.entity.Loan
import com.vtorushin.shared.loan.domain.entity.LoanCondition
import com.vtorushin.shared.loan.domain.entity.LoanRequest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.eq
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
@ExtendWith(MockitoExtension::class)
internal class LoanRemoteRepositoryTest {
    private val api: LoanApi = mock()
    private val auth = "Token"
    private val loanRemoteRepository = LoanRemoteRepository(api, auth)

    @Test
    fun `getLoanCondition EXPECT loanCondition`() = runTest {
        val loanCondition = LoanCondition(100, 10.2, 4)
        whenever(api.getLoanCondition(anyString())).thenReturn(loanCondition.toDto())

        val actual = loanRemoteRepository.getLoanCondition()
        assertEquals(loanCondition, actual)
    }

    @Test
    fun `requestLoan EXPECT loan`() = runTest  {
        val request = LoanRequest(1, "2", "3", 4.5, 6, "7")
        val loan: Loan = mock()
        val loanDto: LoanDto = mock()
        whenever(loanDto.mapToDomain()).thenReturn(loan)
        whenever(api.createNewLoan(anyString(), eq(request.toDto()))).thenReturn(loanDto)

        val actual = loanRemoteRepository.requestLoan(request)
        assertEquals(loan, actual)
    }

    @Test
    fun `getLoans EXPECT loans`() = runTest {
        val loan: Loan = mock()
        val loanDto: LoanDto = mock()
        val loans = listOf(loan, loan, loan)
        whenever(loanDto.mapToDomain()).thenReturn(loan)
        whenever(api.getLoansList(anyString())).thenReturn(loans.map { loanDto })

        val actual = loanRemoteRepository.getLoans()
        assertEquals(loans, actual)
    }

    @Test
    fun `getLoan EXPECT loan`() = runTest {
        val loan: Loan = mock()
        val loanDto: LoanDto = mock()
        whenever(loanDto.mapToDomain()).thenReturn(loan)
        whenever(api.getLoanById(anyString(), anyInt())).thenReturn(loanDto)

        val actual = loanRemoteRepository.getLoan(6)
        assertEquals(loan, actual)
    }
}