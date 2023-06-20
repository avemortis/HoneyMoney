package com.vtorushin.feature.login.di

import androidx.savedstate.SavedStateRegistryOwner

interface LoginComponentOwner {
    fun addLoginComponent(savedStateRegistryOwner: SavedStateRegistryOwner): LoginComponent
    fun clearLoginComponent()
}