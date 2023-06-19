package com.vtorushin.feature.setting.di

import android.content.Context
import androidx.savedstate.SavedStateRegistryOwner
import com.vtorushin.feature.setting.presentation.SettingViewModel
import com.vtorushin.feature.setting.presentation.SettingViewModelFactory
import com.vtorushin.shared.setting.data.repository.SettingsRepositoryImpl
import com.vtorushin.shared.setting.domain.repository.SettingsRepository
import dagger.Module
import dagger.Provides

@Module
class SettingModule {
    @Provides
    @SettingScope
    fun bindSettingRepository(context: Context): SettingsRepository =
        SettingsRepositoryImpl(context)

    @Provides
    @SettingScope
    fun provideSettingViewModel(
        savedStateRegistryOwner: SavedStateRegistryOwner,
        repository: SettingsRepository
    ): SettingViewModel {
        return SettingViewModelFactory(
            savedStateRegistryOwner,
            repository
        ).create(SettingViewModel::class.java)
    }
}