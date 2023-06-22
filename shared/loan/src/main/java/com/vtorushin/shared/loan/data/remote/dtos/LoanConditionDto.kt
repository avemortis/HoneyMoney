package com.vtorushin.shared.loan.data.remote.dtos

import com.google.gson.annotations.SerializedName
import com.vtorushin.shared.loan.domain.entity.LoanCondition
import com.vtorushin.shared.loan.utils.DataMapper

data class LoanConditionDto(
    @SerializedName("maxAmount") val maxAmount: Int,
    @SerializedName("percent") val percent: Double,
    @SerializedName("period") val period: Int
) : DataMapper<LoanCondition> {
    override fun mapToDomain() = LoanCondition(maxAmount, percent, period)
}

fun LoanCondition.toDto() = LoanConditionDto(maxAmount, percent, period)