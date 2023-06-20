package com.vtorushin.feature.authoption.di

import androidx.savedstate.SavedStateRegistryOwner
import com.vtorushin.feature.authoption.presentation.AuthOptionRouter
import com.vtorushin.feature.authoption.presentation.AuthOptionViewModel
import com.vtorushin.feature.authoption.presentation.AuthOptionViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class AuthOptionModule {
    @Provides
    @AuthOptionScope
    fun provideViewModel(
        savedStateRegistryOwner: SavedStateRegistryOwner,
        router: AuthOptionRouter
    ): AuthOptionViewModel {
        return AuthOptionViewModelFactory(
            savedStateRegistryOwner,
            router
        ).create(AuthOptionViewModel::class.java)
    }
}