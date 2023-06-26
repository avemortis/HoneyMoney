package com.vtorushin.features.loan.take.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.vtorushin.feature.loan.take.R
import com.vtorushin.feature.loan.take.databinding.FragmentLoanTakeBinding
import com.vtorushin.features.loan.take.di.component
import com.vtorushin.features.loan.take.presentation.LoanTakeUiState
import kotlinx.coroutines.launch

class LoanTakeFragment : Fragment() {
    private val viewModel by lazy { component().viewModel() }
    private var binding: FragmentLoanTakeBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null)
            viewModel.load()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoanTakeBinding.inflate(inflater, container, false)
        lifecycleScope.launch {
            viewModel.state.collect {
                when (it) {
                    LoanTakeUiState.ServerError -> error()
                    LoanTakeUiState.Loading -> loadingState()
                    is LoanTakeUiState.State -> drawCondition(it)
                    LoanTakeUiState.Successes -> successes()
                    LoanTakeUiState.TooBigNumber -> tooBigNumber()
                    LoanTakeUiState.EmptyInput -> emptyError()
                }
            }
        }
        binding?.let {
            it.takeLoanButton.setOnClickListener { viewModel.onTakeLoan() }
            it.amountInput.doAfterTextChanged { text -> viewModel.inputAmount = text.toString() }
            it.cancelButton.setOnClickListener { viewModel.back() }
        }
        return binding?.root
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }

    override fun onDetach() {
        super.onDetach()
        viewModel.inputAmount = String()
    }

    private fun emptyError() {
        binding?.amountInput?.error = getString(R.string.cannot_be_empty)
    }

    private fun tooBigNumber() {
        binding?.amountInput?.error = getString(R.string.too_big_number)
    }

    private fun loadingState() {
        binding?.content?.visibility = View.INVISIBLE
        binding?.progressBar?.visibility = View.VISIBLE
    }

    private fun error() {
        Toast.makeText(requireContext(), getString(R.string.network_error), Toast.LENGTH_SHORT)
            .show()
    }

    private fun drawCondition(state: LoanTakeUiState.State) {
        binding?.let { binding ->
            binding.content.visibility = View.VISIBLE
            binding.progressBar.visibility = View.INVISIBLE
            state.let {
                binding.percentValue.text = it.loanCondition.percent.toString()
                binding.periodValue.text = it.loanCondition.period.toString()
                binding.maxAmountValue.text = it.loanCondition.maxAmount.toString()
            }
        }

    }

    private fun successes() {
        Toast.makeText(requireContext(), getString(R.string.successes), Toast.LENGTH_SHORT).show()
        binding?.let { binding ->
            binding.correctIcon.visibility = View.VISIBLE
            binding.takeLoanButton.visibility = View.GONE
            binding.amountInput.isEnabled = false
            binding.cancelButton.text = getString(R.string.back)
        }
    }
}