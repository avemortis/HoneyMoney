package com.vtorushin.shared.loan.data.remote.api

import com.vtorushin.shared.loan.data.remote.dtos.LoanConditionDto
import com.vtorushin.shared.loan.data.remote.dtos.LoanDto
import com.vtorushin.shared.loan.data.remote.dtos.LoanRequestDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface LoanApi  {
    @POST("/loans")
    suspend fun createNewLoan(@Body loanRequest: LoanRequestDto) : LoanDto

    @GET("/loans/{id}")
    suspend fun getLoanById(@Path("id") id: Int) : LoanDto

    @GET("/loans/all")
    suspend fun getLoansList() : List<LoanDto>

    @GET("/loans/conditions")
    suspend fun getLoanCondition() : LoanConditionDto
}