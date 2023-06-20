package com.vtorushin.app.di

import com.vtorushin.app.ui.MainActivity

interface AppComponentOwner {
    fun addAppComponent(activity: MainActivity) : AppComponent
}