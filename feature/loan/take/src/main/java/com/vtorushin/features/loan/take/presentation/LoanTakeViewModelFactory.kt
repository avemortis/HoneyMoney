package com.vtorushin.features.loan.take.presentation

import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.vtorushin.shared.loan.domain.repository.LoanConditionRepository
import com.vtorushin.shared.loan.domain.repository.LoanIssueRepository
import com.vtorushin.shared.loan.domain.repository.LoanRepository
import com.vtorushin.shared.loan.domain.usecases.CreateLoanUseCase
import com.vtorushin.shared.loan.domain.usecases.GetLoanConditionUseCase
import com.vtorushin.shared.loan.domain.usecases.GetLoansUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

@Suppress("UNCHECKED_CAST")
class LoanTakeViewModelFactory @AssistedInject constructor(
    @Assisted private val savedStateRegistryOwner: SavedStateRegistryOwner,
    @Assisted private val loanConditionRepository: LoanConditionRepository,
    @Assisted private val loanIssueRepository: LoanIssueRepository,
    @Assisted private val router: LoanTakeRouter
) : AbstractSavedStateViewModelFactory(savedStateRegistryOwner, null) {
    override fun <T : ViewModel> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        return when (modelClass) {
            LoanTakeViewModel::class.java ->
                LoanTakeViewModel(
                    GetLoanConditionUseCase(loanConditionRepository),
                    CreateLoanUseCase(loanIssueRepository),
                    router
                ) as T
            else -> throw IllegalStateException("Unknown viewModel")
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(
            savedStateRegistryOwner: SavedStateRegistryOwner,
            loanConditionRepository: LoanConditionRepository,
            loanIssueRepository: LoanIssueRepository,
            router: LoanTakeRouter
        ): LoanTakeViewModelFactory
    }
}