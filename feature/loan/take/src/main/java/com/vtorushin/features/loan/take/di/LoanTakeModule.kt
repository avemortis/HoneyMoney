package com.vtorushin.features.loan.take.di

import androidx.savedstate.SavedStateRegistryOwner
import com.vtorushin.features.loan.take.presentation.LoanTakeRouter
import com.vtorushin.features.loan.take.presentation.LoanTakeViewModel
import com.vtorushin.features.loan.take.presentation.LoanTakeViewModelFactory
import com.vtorushin.shared.loan.domain.repository.LoanConditionRepository
import com.vtorushin.shared.loan.domain.repository.LoanIssueRepository
import com.vtorushin.shared.setting.domain.repository.SettingsRepository
import dagger.Module
import dagger.Provides

@Module
class LoanTakeModule {
    @Provides
    @LoanTakeScope
    fun provideViewModel(
        savedStateRegistryOwner: SavedStateRegistryOwner,
        loanConditionRepository: LoanConditionRepository,
        loanIssueRepository: LoanIssueRepository,
        loanSettingsRepository: SettingsRepository,
        router: LoanTakeRouter
    ): LoanTakeViewModel {
        return LoanTakeViewModelFactory(
            savedStateRegistryOwner,
            loanConditionRepository,
            loanIssueRepository,
            loanSettingsRepository,
            router
        ).create(LoanTakeViewModel::class.java)
    }
}