package com.vtorushin.feature.registration.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.vtorushin.feature.registration.R
import com.vtorushin.feature.registration.databinding.FragmentRegistrationBinding
import com.vtorushin.feature.registration.di.clearComponent
import com.vtorushin.feature.registration.di.component
import com.vtorushin.feature.registration.presentation.RegistrationError
import com.vtorushin.feature.registration.presentation.RegistrationUiState
import kotlinx.coroutines.launch

class RegistrationFragment : Fragment() {
    private val viewModel by lazy { component().viewModel() }
    private var binding: FragmentRegistrationBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        subscribe()
        binding?.let { binding ->
            binding.registrationButton.setOnClickListener { viewModel.register() }
            binding.loginEditText.doAfterTextChanged { text -> viewModel.login = text.toString() }
            binding.passwordEditText.doAfterTextChanged { text ->
                viewModel.password = text.toString()
            }
        }
        return binding?.root
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    private fun subscribe() {
        lifecycleScope.launch {
            viewModel.state.collect {
                when (it) {
                    is RegistrationUiState.Error -> handleErrorState(it)
                    RegistrationUiState.Successes -> handleSuccessesState()
                    is RegistrationUiState.State -> drawState(it)
                }
            }
        }
    }

    private fun handleErrorState(state: RegistrationUiState.Error) {
        when (state.error) {
            RegistrationError.USER_ALREADY_EXIST -> showToast(getString(R.string.user_already_exist))
            RegistrationError.NETWORK_ERROR -> showToast(getString(R.string.network_error))
            RegistrationError.LOGIN_EMPTY -> setErrorLoginEmpty()
            RegistrationError.PASSWORD_EMPTY -> setErrorPasswordEmpty()
        }
    }

    private fun setErrorLoginEmpty() {
        binding?.loginEditText?.error = getString(R.string.cannot_be_empty)
    }

    private fun setErrorPasswordEmpty() {
        binding?.passwordEditText?.error = getString(R.string.cannot_be_empty)
    }

    private fun handleSuccessesState() {
        clearComponent()
        showToast(getString(R.string.welcome))
    }

    private fun drawState(state: RegistrationUiState.State) {
        binding?.let { binding ->
            binding.loginEditText.setText(state.login)
            binding.passwordEditText.setText(state.password)
        }
    }

    private fun showToast(text: String) {
        Toast.makeText(
            requireContext(),
            text,
            Toast.LENGTH_SHORT
        ).show()
    }
}