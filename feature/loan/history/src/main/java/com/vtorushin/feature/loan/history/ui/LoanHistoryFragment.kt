package com.vtorushin.feature.loan.history.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentResultListener
import androidx.lifecycle.lifecycleScope
import com.vtorushin.feature.loan.history.R
import com.vtorushin.feature.loan.history.databinding.FragmentLoanHistoryBinding
import com.vtorushin.feature.loan.history.di.component
import com.vtorushin.feature.loan.history.presentation.LoanHistoryUiState
import com.vtorushin.feature.loan.utils.listenForLoanTaken
import com.vtorushin.shared.loan.domain.entity.Loan
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class LoanHistoryFragment : Fragment() {
    private val viewModel by lazy { component().viewModel() }
    private var binding: FragmentLoanHistoryBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoanHistoryBinding.inflate(inflater, container, false)
        listenForLoanTaken { viewModel.refresh() }
        subscribeState()
        binding?.let { binding ->
            binding.swipeRefresh.setOnRefreshListener {
                viewModel.refresh()
                binding.swipeRefresh.isRefreshing = false
            }
            binding.takeLoanButton.setOnClickListener { viewModel.takeNewLoan() }
        }

        return binding?.root
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    private fun subscribeState() {
        lifecycleScope.launch {
            viewModel.state.collectLatest {
                when (it) {
                    LoanHistoryUiState.Loading -> loadingState()
                    LoanHistoryUiState.EmptyList -> emptyListState()
                    LoanHistoryUiState.ServerError -> errorState(getString(R.string.failed_to_connect))
                    is LoanHistoryUiState.Content -> drawContent(it.loans)
                }
            }
        }
    }

    private fun loadingState() {
        binding?.let { binding ->
            binding.loansRecyclerView.visibility = View.INVISIBLE
            binding.centerText.visibility = View.INVISIBLE
            binding.loanHistoryProgressBar.visibility = View.VISIBLE
        }
    }

    private fun emptyListState() {
        binding?.let { binding ->
            binding.loansRecyclerView.visibility = View.INVISIBLE
            binding.centerText.visibility = View.VISIBLE
            binding.centerText.text = getString(R.string.you_have_not_taken_any_loans)
            binding.loanHistoryProgressBar.visibility = View.INVISIBLE
        }
    }

    private fun errorState(error: String) {
        binding?.let { binding ->
            binding.centerText.visibility = View.VISIBLE
            binding.centerText.text = error
            binding.loanHistoryProgressBar.visibility = View.INVISIBLE
            Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
        }
    }

    private fun drawContent(content: List<Loan>) {
        binding?.let { binding ->
            binding.loansRecyclerView.visibility = View.VISIBLE
            binding.centerText.visibility = View.INVISIBLE
            binding.loanHistoryProgressBar.visibility = View.INVISIBLE
            val adapter = LoanHistoryAdapter(content) {
                viewModel.overviewLoan(it)
            }
            binding.loansRecyclerView.adapter = adapter
        }
    }
}