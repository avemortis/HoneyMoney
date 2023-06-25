package com.vtorushin.features.loan.take.di

import androidx.fragment.app.Fragment
import androidx.savedstate.SavedStateRegistryOwner
import com.vtorushin.features.loan.take.presentation.LoanTakeViewModel
import com.vtorushin.shared.loan.domain.repository.LoanConditionRepository
import com.vtorushin.shared.loan.domain.repository.LoanIssueRepository
import dagger.BindsInstance
import dagger.Subcomponent

@LoanTakeScope
@Subcomponent(modules = [LoanTakeModule::class])
interface LoanTakeComponent {
    fun viewModel(): LoanTakeViewModel

    @Subcomponent.Factory
    interface Factory {
        fun create(
            @BindsInstance savedStateRegistryOwner: SavedStateRegistryOwner,
            @BindsInstance loanConditionRepository: LoanConditionRepository,
            @BindsInstance loanIssueRepository: LoanIssueRepository
        ): LoanTakeComponent
    }
}

internal fun Fragment.component() =
    (requireContext().applicationContext as LoanTakeComponentOwner)
        .addLoanTakeComponent(this)

internal fun Fragment.clearComponent() =
    (requireContext().applicationContext as LoanTakeComponentOwner)
        .clearLoanTakeComponent()
