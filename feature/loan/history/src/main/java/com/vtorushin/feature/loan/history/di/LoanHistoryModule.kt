package com.vtorushin.feature.loan.history.di

import androidx.savedstate.SavedStateRegistryOwner
import com.vtorushin.feature.loan.history.presentation.LoanHistoryRouter
import com.vtorushin.feature.loan.history.presentation.LoanHistoryViewModel
import com.vtorushin.feature.loan.history.presentation.LoanHistoryViewModelFactory
import com.vtorushin.shared.loan.domain.repository.LoanRepository
import dagger.Module
import dagger.Provides

@Module
class LoanHistoryModule {
    @Provides
    @LoanHistoryScope
    fun provideViewModel(
        savedStateRegistryOwner: SavedStateRegistryOwner,
        loanRepository: LoanRepository,
        router: LoanHistoryRouter
    ): LoanHistoryViewModel {
        return LoanHistoryViewModelFactory(
            savedStateRegistryOwner,
            loanRepository,
            router
        ).create(LoanHistoryViewModel::class.java)
    }
}