package com.vtorushin.feature.loan.history.di

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.savedstate.SavedStateRegistryOwner
import com.vtorushin.feature.loan.history.presentation.LoanHistoryViewModel
import com.vtorushin.shared.loan.domain.repository.LoanRepository
import dagger.BindsInstance
import dagger.Subcomponent

@LoanHistoryScope
@Subcomponent(modules = [LoanHistoryModule::class])
interface LoanHistoryComponent {
    fun viewModel(): LoanHistoryViewModel

    @Subcomponent.Factory
    interface Factory {
        fun create(
            @BindsInstance savedStateRegistryOwner: SavedStateRegistryOwner,
            @BindsInstance loanRepository: LoanRepository,
            @BindsInstance context: Context
        ): LoanHistoryComponent
    }
}

internal fun Fragment.component() =
    (requireContext().applicationContext as LoanHistoryComponentOwner)
        .addLoanHistoryComponent(this)

internal fun Fragment.clearComponent() =
    (requireContext().applicationContext as LoanHistoryComponentOwner)
        .clearLoanHistoryComponent()
