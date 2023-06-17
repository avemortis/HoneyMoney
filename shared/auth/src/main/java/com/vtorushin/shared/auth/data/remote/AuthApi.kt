package com.vtorushin.shared.auth.data.remote

import com.vtorushin.shared.auth.domain.entity.AuthBody
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("/login")
    suspend fun login(@Body authBody: AuthBody): String
}