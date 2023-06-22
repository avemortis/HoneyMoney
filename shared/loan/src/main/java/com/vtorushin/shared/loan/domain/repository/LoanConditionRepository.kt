package com.vtorushin.shared.loan.domain.repository

import com.vtorushin.shared.loan.domain.entity.LoanCondition

interface LoanConditionRepository {
    suspend fun getLoanCondition() : LoanCondition
}