package com.vtorushin.shared.auth.domain.repository

interface TokenRepository {
    fun get(): String?
    fun set(auth: String)
}