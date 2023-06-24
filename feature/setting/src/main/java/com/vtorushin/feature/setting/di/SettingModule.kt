package com.vtorushin.feature.setting.di

import androidx.savedstate.SavedStateRegistryOwner
import com.vtorushin.feature.setting.presentation.SettingRouter
import com.vtorushin.feature.setting.presentation.SettingViewModel
import com.vtorushin.feature.setting.presentation.SettingViewModelFactory
import com.vtorushin.shared.setting.domain.repository.SettingsRepository
import dagger.Module
import dagger.Provides

@Module
class SettingModule {
    @Provides
    @SettingScope
    fun provideSettingViewModel(
        savedStateRegistryOwner: SavedStateRegistryOwner,
        repository: SettingsRepository,
        router: SettingRouter
    ): SettingViewModel {
        return SettingViewModelFactory(
            savedStateRegistryOwner,
            repository,
            router
        ).create(SettingViewModel::class.java)
    }
}