package com.vtorushin.feature.loan.history.presentation

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.vtorushin.feature.loan.history.utils.InstantTaskExecutorExtension
import com.vtorushin.feature.loan.history.utils.TestCoroutineExtension
import com.vtorushin.shared.loan.domain.entity.Loan
import com.vtorushin.shared.loan.domain.usecases.GetLoansUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.inOrder
import org.mockito.kotlin.whenever
import kotlin.reflect.KClass

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
@ExtendWith(
    MockitoExtension::class,
    TestCoroutineExtension::class,
    InstantTaskExecutorExtension::class
)
internal class LoanHistoryViewModelTest {
    private val getLoanUseCase: GetLoansUseCase = mock()
    private val router: LoanHistoryRouter = mock()
    private val viewModel = LoanHistoryViewModel(getLoanUseCase, router)
    private val list: List<Loan> = mock()


    @Test
    fun `view model created EXPECT loading state`() = runTest {
        val expected = LoanHistoryUiState.Loading
        val actual = viewModel.state.first()
        assertEquals(expected, actual)
    }

    @Test
    fun `view model second item EXPECT content state`() = runTest {
        whenever(getLoanUseCase.invoke()).thenReturn(list)
        val expected = listOf(LoanHistoryUiState.Loading::class, LoanHistoryUiState.Content::class)
        val actual: MutableList<KClass<out LoanHistoryUiState>> = mutableListOf()
        val job = launch {
            viewModel.state.take(2).collect {
                actual.add(it::class)
            }
            assertEquals(expected, actual)
        }
        job.start()
        delay(1)
        viewModel.refresh()
    }
}