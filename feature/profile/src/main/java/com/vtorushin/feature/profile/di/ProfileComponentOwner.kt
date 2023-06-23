package com.vtorushin.feature.profile.di

import androidx.savedstate.SavedStateRegistryOwner

interface ProfileComponentOwner {
    fun addProfileComponent(savedStateRegistryOwner: SavedStateRegistryOwner): ProfileComponent
    fun clearProfileComponent()
}