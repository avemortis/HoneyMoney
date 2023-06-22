package com.vtorushin.component.tab.di

import androidx.savedstate.SavedStateRegistryOwner

interface TabsComponentOwner {
    fun addTabsComponent(savedStateRegistryOwner: SavedStateRegistryOwner): TabsComponent
    fun clearTabsComponent()
}