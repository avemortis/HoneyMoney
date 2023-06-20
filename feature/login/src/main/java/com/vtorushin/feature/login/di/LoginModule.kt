package com.vtorushin.feature.login.di


import androidx.savedstate.SavedStateRegistryOwner
import com.google.gson.GsonBuilder
import com.vtorushin.feature.login.presentation.LoginRouter
import com.vtorushin.feature.login.presentation.LoginViewModel
import com.vtorushin.feature.login.presentation.LoginViewModelFactory
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
class LoginModule {
    @Provides
    @LoginScope
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
    fun provideAuthRepository(api: AuthApi) : AuthRepository = AuthRepositoryImpl(api)

    @Provides
    @LoginScope
    fun provideViewModel(
        savedStateRegistryOwner: SavedStateRegistryOwner,
        authRepository: AuthRepository,
        tokenRepository: TokenRepository,
        router: LoginRouter
    ): LoginViewModel {
        return LoginViewModelFactory(
            savedStateRegistryOwner,
            authRepository,
            tokenRepository,
            router
        ).create(LoginViewModel::class.java)
    }
}