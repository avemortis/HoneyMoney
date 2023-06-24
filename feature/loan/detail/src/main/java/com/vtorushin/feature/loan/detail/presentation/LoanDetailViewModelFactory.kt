package com.vtorushin.feature.loan.detail.presentation

import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.vtorushin.shared.loan.domain.repository.LoanRepository
import com.vtorushin.shared.loan.domain.usecases.GetLoanUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

@Suppress("UNCHECKED_CAST")
class LoanDetailViewModelFactory @AssistedInject constructor(
    @Assisted private val savedStateRegistryOwner: SavedStateRegistryOwner,
    @Assisted private val loanRepository: LoanRepository,
    @Assisted private val router: LoanDetailRouter
) : AbstractSavedStateViewModelFactory(savedStateRegistryOwner, null) {
    override fun <T : ViewModel> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        return when (modelClass) {
            LoanDetailViewModel::class.java ->
                LoanDetailViewModel(
                    GetLoanUseCase(loanRepository),
                    router
                ) as T
            else -> throw IllegalStateException("Unknown viewModel")
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(
            savedStateRegistryOwner: SavedStateRegistryOwner,
            repository: LoanRepository,
            router: LoanDetailRouter
        ): LoanDetailViewModelFactory
    }
}