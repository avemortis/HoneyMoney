package com.vtorushin.shared.loan.data.repository

import com.vtorushin.shared.loan.data.remote.api.LoanApi
import com.vtorushin.shared.loan.data.remote.dtos.toDto
import com.vtorushin.shared.loan.domain.entity.LoanRequest
import com.vtorushin.shared.loan.domain.repository.LoanConditionRepository
import com.vtorushin.shared.loan.domain.repository.LoanIssueRepository
import com.vtorushin.shared.loan.domain.repository.LoanRepository
import javax.inject.Inject

class LoanRemoteRepository @Inject constructor(
    private val api: LoanApi,
    private val auth: String
) : LoanRepository,
    LoanIssueRepository,
    LoanConditionRepository {
    override suspend fun getLoanCondition() = api.getLoanCondition(auth).mapToDomain()

    override suspend fun requestLoan(request: LoanRequest) =
        api.createNewLoan(auth, request.toDto()).mapToDomain()

    override suspend fun getLoans() = api.getLoansList(auth).map { it.mapToDomain() }

    override suspend fun getLoan(id: Int) = api.getLoanById(auth, id).mapToDomain()
}