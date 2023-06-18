package com.vtorushin.feature.registration.di

import android.content.Context
import androidx.savedstate.SavedStateRegistryOwner

interface RegistrationComponentOwner {
    fun addComponent(
        savedStateRegistryOwner: SavedStateRegistryOwner,
        context: Context
    ): RegistrationComponent

    fun clear()
}