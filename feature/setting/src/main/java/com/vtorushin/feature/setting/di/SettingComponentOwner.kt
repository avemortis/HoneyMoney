package com.vtorushin.feature.setting.di

import androidx.savedstate.SavedStateRegistryOwner

interface SettingComponentOwner {
    fun addSettingComponent(
        savedStateRegistryOwner: SavedStateRegistryOwner
    ): SettingComponent

    fun clearSettingComponent()
}