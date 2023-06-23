package com.vtorushin.feature.profile.di

import androidx.savedstate.SavedStateRegistryOwner
import com.vtorushin.feature.profile.presentation.ProfileRouter
import com.vtorushin.feature.profile.presentation.ProfileViewModel
import com.vtorushin.feature.profile.presentation.ProfileViewModelFactory
import com.vtorushin.shared.setting.domain.repository.SettingsRepository
import dagger.Module
import dagger.Provides

@Module
class ProfileModule {
    @Provides
    @ProfileScope
    fun provideSettingViewModel(
        savedStateRegistryOwner: SavedStateRegistryOwner,
        repository: SettingsRepository,
        router: ProfileRouter
    ): ProfileViewModel {
        return ProfileViewModelFactory(
            savedStateRegistryOwner,
            repository,
            router
        ).create(ProfileViewModel::class.java)
    }
}