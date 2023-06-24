package com.vtorushin.feature.loan.history.presentation

import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.vtorushin.shared.loan.domain.repository.LoanRepository
import com.vtorushin.shared.loan.domain.usecases.GetLoansUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

@Suppress("UNCHECKED_CAST")
class LoanHistoryViewModelFactory @AssistedInject constructor(
    @Assisted private val savedStateRegistryOwner: SavedStateRegistryOwner,
    @Assisted private val loanRepository: LoanRepository,
    @Assisted private val router: LoanHistoryRouter
) : AbstractSavedStateViewModelFactory(savedStateRegistryOwner, null) {
    override fun <T : ViewModel> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        return when (modelClass) {
            LoanHistoryViewModel::class.java ->
                LoanHistoryViewModel(
                    GetLoansUseCase(loanRepository),
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
            router: LoanHistoryRouter
        ): LoanHistoryViewModelFactory
    }
}