package com.vtorushin.feature.authoption.di

import androidx.savedstate.SavedStateRegistryOwner

interface AuthOptionComponentOwner {
    fun addAuthOptionComponent(savedStateRegistryOwner: SavedStateRegistryOwner): AuthOptionComponent
    fun clearAuthOptionComponent()
}