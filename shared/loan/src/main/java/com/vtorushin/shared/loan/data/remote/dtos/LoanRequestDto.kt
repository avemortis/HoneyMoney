package com.vtorushin.shared.loan.data.remote.dtos

import com.google.gson.annotations.SerializedName
import com.vtorushin.shared.loan.domain.entity.LoanRequest
import com.vtorushin.shared.loan.utils.DataMapper

data class LoanRequestDto(
    @SerializedName("amount") val amount: Long,
    @SerializedName("firstName") val firstName: String,
    @SerializedName("lastName") val lastName: String,
    @SerializedName("percent") val percent: Double,
    @SerializedName("period") val period: Int,
    @SerializedName("phoneNumber") val phoneNumber: String,
) : DataMapper<LoanRequest> {
    override fun mapToDomain() =
        LoanRequest(amount, firstName, lastName, percent, period, phoneNumber)
}

fun LoanRequest.toDto() = LoanRequestDto(amount, firstName, lastName, percent, period, phoneNumber)