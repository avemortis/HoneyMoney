package com.vtorushin.shared.loan.domain.entity

data class LoanCondition(
    val maxAmount: Int,
    val percent: Double,
    val period: Int
)
