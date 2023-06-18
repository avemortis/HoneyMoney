package com.vtorushin.feature.registration.di

import android.content.Context
import androidx.savedstate.SavedStateRegistryOwner
import com.google.gson.GsonBuilder
import com.vtorushin.feature.registration.presentation.RegistrationRouter
import com.vtorushin.feature.registration.presentation.RegistrationViewModel
import com.vtorushin.feature.registration.presentation.RegistrationViewModelFactory
import com.vtorushin.shared.auth.data.remote.AuthApi
import com.vtorushin.shared.auth.data.repository.AuthRepositoryImpl
import com.vtorushin.shared.auth.domain.repository.AuthRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

@Module
class RegistrationModule {
    @Provides
    @RegistrationScope
    fun provideAuthApi() : AuthApi {
        val gson = GsonBuilder()
            .setPrettyPrinting()
            .setLenient()
            .create()

        return Retrofit.Builder()
            .baseUrl("https://shiftlab.cft.ru:7777")
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(AuthApi::class.java)
    }

    @Provides
    fun bindAuthRepository(context: Context, api: AuthApi) : AuthRepository = AuthRepositoryImpl(context, api)

    @Provides
    @RegistrationScope
    fun provideViewModel(
        savedStateRegistryOwner: SavedStateRegistryOwner,
        repository: AuthRepository,
        router: RegistrationRouter
    ): RegistrationViewModel {
        return RegistrationViewModelFactory(
            savedStateRegistryOwner,
            repository,
            router
        ).create(RegistrationViewModel::class.java)
    }
}