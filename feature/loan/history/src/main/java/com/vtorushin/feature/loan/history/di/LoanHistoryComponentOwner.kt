package com.vtorushin.feature.loan.history.di

import androidx.savedstate.SavedStateRegistryOwner

interface LoanHistoryComponentOwner {
    fun addLoanHistoryComponent(savedStateRegistryOwner: SavedStateRegistryOwner): LoanHistoryComponent
    fun clearLoanHistoryComponent()
}