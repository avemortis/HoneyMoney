package com.vtorushin.feature.loan.di

import com.vtorushin.shared.loan.domain.repository.LoanRepository
import dagger.Subcomponent

@LoanScope
@Subcomponent(modules = [LoanModule::class])
interface LoanComponent {
    fun provideLoanRepository(): LoanRepository

    @Subcomponent.Builder
    interface Builder {
        fun build(): LoanComponent
    }
}