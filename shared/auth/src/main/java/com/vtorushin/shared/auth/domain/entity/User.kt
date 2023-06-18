package com.vtorushin.shared.auth.domain.entity

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("name") val name: String,
    @SerializedName("role") val role: String
)
