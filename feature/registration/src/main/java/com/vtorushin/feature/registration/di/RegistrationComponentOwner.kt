package com.vtorushin.feature.registration.di

import androidx.savedstate.SavedStateRegistryOwner

interface RegistrationComponentOwner {
    fun addRegisterComponent(savedStateRegistryOwner: SavedStateRegistryOwner): RegistrationComponent
    fun clearRegisterComponent()
}