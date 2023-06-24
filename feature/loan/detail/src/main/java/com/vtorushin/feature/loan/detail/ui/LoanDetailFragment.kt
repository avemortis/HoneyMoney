package com.vtorushin.feature.loan.detail.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.vtorushin.feature.loan.detail.R
import com.vtorushin.feature.loan.detail.databinding.FragmentLoanDetailBinding
import com.vtorushin.feature.loan.detail.di.component
import com.vtorushin.feature.loan.detail.presentation.LoanDetailUiState
import com.vtorushin.shared.loan.domain.entity.LoanStatusModel
import com.vtorushin.utils.datetime.trimTime
import kotlinx.coroutines.launch
import java.util.*

class LoanDetailFragment : Fragment() {
    private lateinit var binding: FragmentLoanDetailBinding
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
        binding = FragmentLoanDetailBinding.inflate(inflater, container, false)
        binding.backButton.setOnClickListener { viewModel.back() }
        observeState()
        return binding.root
    }

    private fun observeState() {
        lifecycleScope.launch {
            viewModel.state.collect {
                when (it) {
                    is LoanDetailUiState.Content -> drawState(it)
                    LoanDetailUiState.Error -> error()
                    LoanDetailUiState.Loading -> loading()
                }
            }
        }
    }

    private fun drawState(state: LoanDetailUiState.Content) {
        binding.content.visibility = View.VISIBLE
        binding.progressBar.visibility = View.INVISIBLE
        binding.loanDateValue.text = trimTime(state.loan.date)
        binding.loanAmountValue.text = state.loan.amount.toString()
        binding.loanProcentValue.text = state.loan.percent.toString()
        binding.nameValue.text = state.loan.firstName
        binding.lastNameValue.text = state.loan.lastName
        binding.phoneValue.text = state.loan.phoneNumber
        binding.status.text = state.loan.state.name
        binding.id.text = getString(R.string.id, state.loan.id)
        binding.message.text = when (state.loan.state) {
            LoanStatusModel.APPROVED -> getString(R.string.approved_message)
            LoanStatusModel.REGISTERED -> getString(R.string.registered_message)
            LoanStatusModel.REJECTED -> getString(R.string.reject_message)
        }
    }

    private fun error() {
        binding.content.visibility = View.INVISIBLE
        binding.progressBar.visibility = View.INVISIBLE
        Toast.makeText(requireContext(), getString(R.string.failed_to_connect), Toast.LENGTH_SHORT)
            .show()
    }

    private fun loading() {
        binding.content.visibility = View.INVISIBLE
        binding.progressBar.visibility = View.VISIBLE
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