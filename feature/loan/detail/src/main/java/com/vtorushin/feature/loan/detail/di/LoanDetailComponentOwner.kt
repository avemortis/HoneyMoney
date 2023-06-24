package com.vtorushin.feature.loan.detail.di

import androidx.savedstate.SavedStateRegistryOwner

interface LoanDetailComponentOwner {
    fun addLoanDetailComponent(savedStateRegistryOwner: SavedStateRegistryOwner): LoanDetailComponent
    fun clearLoanDetailComponent()
}