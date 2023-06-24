package com.vtorushin.feature.loan.history.di

import androidx.savedstate.SavedStateRegistryOwner
import com.google.gson.GsonBuilder
import com.vtorushin.feature.loan.history.presentation.LoanHistoryRouter
import com.vtorushin.feature.loan.history.presentation.LoanHistoryViewModel
import com.vtorushin.feature.loan.history.presentation.LoanHistoryViewModelFactory
import com.vtorushin.shared.auth.domain.repository.TokenRepository
import com.vtorushin.shared.loan.data.remote.api.LoanApi
import com.vtorushin.shared.loan.data.repository.LoanRemoteRepository
import com.vtorushin.shared.loan.domain.repository.LoanRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class LoanHistoryModule {
    @Provides
    @LoanHistoryScope
    fun provideAuthApi(): LoanApi {
        val gson = GsonBuilder()
            .setPrettyPrinting()
            .setLenient()
            .create()

        return Retrofit.Builder()
            .baseUrl("https://shiftlab.cft.ru:7777")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(LoanApi::class.java)
    }

    @Provides
    fun provideAuthRepository(api: LoanApi, tokenRepository: TokenRepository): LoanRepository =
        LoanRemoteRepository(api, tokenRepository.get() ?: throw IllegalStateException("Auth Token is missing"))

    @Provides
    @LoanHistoryScope
    fun provideViewModel(
        savedStateRegistryOwner: SavedStateRegistryOwner,
        loanRepository: LoanRepository,
        router: LoanHistoryRouter
    ): LoanHistoryViewModel {
        return LoanHistoryViewModelFactory(
            savedStateRegistryOwner,
            loanRepository,
            router
        ).create(LoanHistoryViewModel::class.java)
    }
}