package com.vtorushin.shared.auth.domain.entity

import com.google.gson.annotations.SerializedName

data class AuthBody(
    @SerializedName("name") val name: String,
    @SerializedName("password") val password: String
)