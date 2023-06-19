package com.vtorushin.feature.setting.di

import android.content.Context
import androidx.savedstate.SavedStateRegistryOwner

interface SettingComponentOwner {
    fun addSettingComponent(
        savedStateRegistryOwner: SavedStateRegistryOwner
    ): SettingComponent

    fun clearSettingComponent()
}