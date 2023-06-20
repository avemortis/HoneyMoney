package com.vtorushin.app.di

import android.content.Context
import androidx.savedstate.SavedStateRegistryOwner
import com.vtorushin.app.presentation.MainViewModel
import com.vtorushin.app.presentation.MainViewModelFactory
import com.vtorushin.app.ui.MainActivity
import com.vtorushin.feature.login.di.LoginScope
import com.vtorushin.feature.login.presentation.LoginRouter
import com.vtorushin.feature.login.presentation.LoginViewModel
import com.vtorushin.feature.login.presentation.LoginViewModelFactory
import com.vtorushin.shared.auth.data.repository.TokenRepositoryImpl
import com.vtorushin.shared.auth.domain.repository.AuthRepository
import com.vtorushin.shared.auth.domain.repository.TokenRepository
import com.vtorushin.shared.setting.data.repository.SettingsRepositoryImpl
import com.vtorushin.shared.setting.domain.repository.SettingsRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {
    @Provides
    @Singleton
    fun provideTokenRepository(context: Context) : TokenRepository = TokenRepositoryImpl(context)

    @Provides
    @Singleton
    fun settingsRepository(context: Context) : SettingsRepository = SettingsRepositoryImpl(context)

    @Provides
    @Singleton
    fun provideViewModel(
        savedStateRegistryOwner: MainActivity,
        settingsRepository: SettingsRepository,
        tokenRepository: TokenRepository
    ): MainViewModel {
        return MainViewModelFactory(
            savedStateRegistryOwner,
            settingsRepository,
            tokenRepository
        ).create(MainViewModel::class.java)
    }
}