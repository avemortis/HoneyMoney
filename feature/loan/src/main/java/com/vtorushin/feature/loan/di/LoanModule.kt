package com.vtorushin.feature.loan.di

import com.google.gson.GsonBuilder
import com.vtorushin.shared.auth.domain.repository.TokenRepository
import com.vtorushin.shared.loan.data.remote.api.LoanApi
import com.vtorushin.shared.loan.data.repository.LoanRemoteRepository
import com.vtorushin.shared.loan.domain.repository.LoanConditionRepository
import com.vtorushin.shared.loan.domain.repository.LoanIssueRepository
import com.vtorushin.shared.loan.domain.repository.LoanRepository
import dagger.Binds
import dagger.BindsInstance
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class LoanModule {
    @Provides
    @LoanScope
    fun provideRemoteRepository(api: LoanApi, tokenRepository: TokenRepository) =
        LoanRemoteRepository(
            api,
            tokenRepository.get() ?: throw IllegalStateException("Auth Token is missing")
        )

    @Provides
    @LoanScope
    fun provideLoanApi(): LoanApi {
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
    @LoanScope
    fun provideLoanRepository(loanRemoteRepository: LoanRemoteRepository): LoanRepository =
        loanRemoteRepository

    @Provides
    @LoanScope
    fun provideLoanConditionRepository(loanRemoteRepository: LoanRemoteRepository): LoanConditionRepository =
        loanRemoteRepository

    @Provides
    @LoanScope
    fun provideLoanIssueRepository(loanRemoteRepository: LoanRemoteRepository): LoanIssueRepository =
        loanRemoteRepository
}