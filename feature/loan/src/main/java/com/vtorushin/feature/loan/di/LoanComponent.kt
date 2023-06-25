package com.vtorushin.feature.loan.di

import com.vtorushin.shared.loan.domain.repository.LoanConditionRepository
import com.vtorushin.shared.loan.domain.repository.LoanIssueRepository
import com.vtorushin.shared.loan.domain.repository.LoanRepository
import dagger.Subcomponent

@LoanScope
@Subcomponent(modules = [LoanModule::class])
interface LoanComponent {
    fun provideLoanRepository(): LoanRepository
    fun provideLoanConditionRepository() : LoanConditionRepository
    fun provideLoanIssueRepository() : LoanIssueRepository

    @Subcomponent.Builder
    interface Builder {
        fun build(): LoanComponent
    }
}