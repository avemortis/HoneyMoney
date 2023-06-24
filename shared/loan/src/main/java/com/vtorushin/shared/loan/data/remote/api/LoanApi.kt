package com.vtorushin.shared.loan.data.remote.api

import com.vtorushin.shared.loan.data.remote.dtos.LoanConditionDto
import com.vtorushin.shared.loan.data.remote.dtos.LoanDto
import com.vtorushin.shared.loan.data.remote.dtos.LoanRequestDto
import retrofit2.http.*

interface LoanApi {
    @POST("/loans")
    suspend fun createNewLoan(
        @Header("Authorization") auth: String,
        @Body loanRequest: LoanRequestDto
    ): LoanDto

    @GET("/loans/{id}")
    suspend fun getLoanById(
        @Header("Authorization") auth: String,
        @Path("id") id: Int
    ): LoanDto

    @GET("/loans/all")
    suspend fun getLoansList(
        @Header("Authorization") auth: String,
    ): List<LoanDto>

    @GET("/loans/conditions")
    suspend fun getLoanCondition(
        @Header("Authorization") auth: String
    ): LoanConditionDto
}