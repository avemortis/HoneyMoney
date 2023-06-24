package com.vtorushin.feature.loan.detail.di

import androidx.savedstate.SavedStateRegistryOwner
import com.vtorushin.feature.loan.detail.presentation.LoanDetailRouter
import com.vtorushin.feature.loan.detail.presentation.LoanDetailViewModel
import com.vtorushin.feature.loan.detail.presentation.LoanDetailViewModelFactory
import com.vtorushin.shared.loan.domain.repository.LoanRepository
import dagger.Module
import dagger.Provides

@Module
class LoanDetailModule {
    @Provides
    @LoanDetailScope
    fun provideViewModel(
        savedStateRegistryOwner: SavedStateRegistryOwner,
        loanRepository: LoanRepository,
        router: LoanDetailRouter
    ): LoanDetailViewModel {
        return LoanDetailViewModelFactory(
            savedStateRegistryOwner,
            loanRepository,
            router
        ).create(LoanDetailViewModel::class.java)
    }
}