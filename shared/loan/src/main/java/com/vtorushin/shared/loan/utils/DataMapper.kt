package com.vtorushin.shared.loan.utils

interface DataMapper<T> {
    fun mapToDomain(): T
}