package com.vtorushin.shared.auth.domain.repository

interface AuthRepository {
    suspend fun generate(name: String, password: String): String
    fun get(): String
    fun set(auth: String)
}