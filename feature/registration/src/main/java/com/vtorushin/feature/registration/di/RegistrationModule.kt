package com.vtorushin.feature.registration.di

import android.content.Context
import androidx.savedstate.SavedStateRegistryOwner
import com.google.gson.GsonBuilder
import com.vtorushin.feature.registration.BuildConfig
import com.vtorushin.feature.registration.presentation.RegistrationRouter
import com.vtorushin.feature.registration.presentation.RegistrationViewModel
import com.vtorushin.feature.registration.presentation.RegistrationViewModelFactory
import com.vtorushin.shared.auth.data.remote.AuthApi
import com.vtorushin.shared.auth.data.repository.AuthRepositoryImpl
import com.vtorushin.shared.auth.data.repository.TokenRepositoryImpl
import com.vtorushin.shared.auth.domain.repository.AuthRepository
import com.vtorushin.shared.auth.domain.repository.TokenRepository
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
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(AuthApi::class.java)
    }

    @Provides
    fun provideAuthRepository(api: AuthApi) : AuthRepository = AuthRepositoryImpl(api)

    @Provides
    @RegistrationScope
    fun provideViewModel(
        savedStateRegistryOwner: SavedStateRegistryOwner,
        authRepository: AuthRepository,
        tokenRepository: TokenRepository,
        router: RegistrationRouter
    ): RegistrationViewModel {
        return RegistrationViewModelFactory(
            savedStateRegistryOwner,
            authRepository,
            tokenRepository,
            router
        ).create(RegistrationViewModel::class.java)
    }
}