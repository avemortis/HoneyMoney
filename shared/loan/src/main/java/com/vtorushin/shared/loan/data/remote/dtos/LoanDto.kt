package com.vtorushin.shared.loan.data.remote.dtos

import com.google.gson.annotations.SerializedName
import com.vtorushin.shared.loan.domain.entity.Loan
import com.vtorushin.shared.loan.domain.entity.LoanStatusModel
import com.vtorushin.shared.loan.utils.DataMapper

data class LoanDto(
    @SerializedName("amount") val amount: Int,
    @SerializedName("date") val date: String,
    @SerializedName("firstName") val firstName: String,
    @SerializedName("id") val id: Int,
    @SerializedName("lastName") val lastName: String,
    @SerializedName("percent") val percent: Double,
    @SerializedName("period") val period: Int,
    @SerializedName("phoneNumber") val phoneNumber: String,
    @SerializedName("state") val state: String
) : DataMapper<Loan> {
    override fun mapToDomain() =
        Loan(amount, date, firstName, id, lastName, percent, period, phoneNumber, LoanStatusModel.valueOf(state))
}

fun Loan.toDto() =
    LoanDto(amount, date, firstName, id, lastName, percent, period, phoneNumber, state.name)