package com.vtorushin.feature.loan.detail.di

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.savedstate.SavedStateRegistryOwner
import com.vtorushin.feature.loan.detail.presentation.LoanDetailViewModel
import com.vtorushin.shared.loan.domain.repository.LoanRepository
import dagger.BindsInstance
import dagger.Subcomponent

@LoanDetailScope
@Subcomponent(modules = [LoanDetailModule::class])
interface LoanDetailComponent {
    fun viewModel(): LoanDetailViewModel

    @Subcomponent.Factory
    interface Factory {
        fun create(
            @BindsInstance savedStateRegistryOwner: SavedStateRegistryOwner,
            @BindsInstance loanRepository: LoanRepository,
            @BindsInstance context: Context
        ): LoanDetailComponent
    }
}

internal fun Fragment.component() =
    (requireContext().applicationContext as LoanDetailComponentOwner)
        .addLoanDetailComponent(this)

internal fun Fragment.clearComponent() =
    (requireContext().applicationContext as LoanDetailComponentOwner)
        .clearLoanDetailComponent()
