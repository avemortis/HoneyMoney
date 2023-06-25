package com.vtorushin.features.loan.take.di

import androidx.savedstate.SavedStateRegistryOwner

interface LoanTakeComponentOwner {
    fun addLoanTakeComponent(savedStateRegistryOwner: SavedStateRegistryOwner): LoanTakeComponent
    fun clearLoanTakeComponent()
}