package com.vtorushin.feature.loan.detail.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.vtorushin.feature.loan.detail.databinding.FragmentLoanDetailBinding
import com.vtorushin.feature.loan.detail.di.component
import java.util.*

class LoanDetailFragment : Fragment() {
    private val viewModel by lazy { component().viewModel() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadLoan(
            arguments?.getInt(LOAN_ID)
                ?: throw MissingFormatArgumentException("Loan id is not passed")
        )

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentLoanDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        private const val LOAN_ID = "Loan Id"

        @JvmStatic
        fun newInstance(loanId: Int) =
            LoanDetailFragment().apply {
                arguments = Bundle().apply {
                    putInt(LOAN_ID, loanId)
                }
            }
    }
}