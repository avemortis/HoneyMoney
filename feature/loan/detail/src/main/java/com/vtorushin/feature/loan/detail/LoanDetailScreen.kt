package com.vtorushin.feature.loan.detail

import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.vtorushin.feature.loan.detail.ui.LoanDetailFragment

fun getLoanDetailScreen(loanId: Int) = FragmentScreen { LoanDetailFragment.newInstance(loanId) }