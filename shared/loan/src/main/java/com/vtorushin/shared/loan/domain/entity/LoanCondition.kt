package com.vtorushin.shared.loan.domain.entity

data class LoanCondition(
    val maxAmount: Long,
    val percent: Double,
    val period: Int
)
