package com.vtorushin.feature.login.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.lifecycleScope
import com.vtorushin.feature.login.R
import com.vtorushin.feature.login.databinding.FragmentLoginBinding
import com.vtorushin.feature.login.di.clear
import com.vtorushin.feature.login.di.component
import com.vtorushin.feature.login.presentation.LoginError
import com.vtorushin.feature.login.presentation.LoginUiState
import kotlinx.coroutines.launch

class LoginFragment : Fragment() {
    private val viewModel by lazy { component().viewModel() }
    private lateinit var binding: FragmentLoginBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        subscribe()
        binding.registrationButton.setOnClickListener { viewModel.register() }
        binding.loginEditText.doAfterTextChanged { text -> viewModel.login = text.toString() }
        binding.passwordEditText.doAfterTextChanged { text -> viewModel.password = text.toString() }
        return binding.root
    }

    private fun subscribe() {
        lifecycleScope.launch {
            viewModel.state.collect {
                when (it) {
                    is LoginUiState.Error -> handleErrorState(it)
                    LoginUiState.Successes -> handleSuccessesState()
                    is LoginUiState.State -> drawState(it)
                }
            }
        }
    }
    private fun handleErrorState(state: LoginUiState.Error) {
        when (state.error) {
            LoginError.USER_NOT_FOUND -> showToast(getString(R.string.user_not_found))
            LoginError.NETWORK_ERROR -> showToast(getString(R.string.network_error))
            LoginError.LOGIN_EMPTY -> setErrorLoginEmpty()
            LoginError.PASSWORD_EMPTY -> setErrorPasswordEmpty()
        }
    }

    private fun setErrorLoginEmpty() {
        binding.loginEditText.error = getString(R.string.cannot_be_empty)
    }

    private fun setErrorPasswordEmpty() {
        binding.passwordEditText.error = getString(R.string.cannot_be_empty)
    }

    private fun handleSuccessesState() {
        clear()
        showToast(getString(R.string.welcome))
    }

    private fun drawState(state: LoginUiState.State) {
        binding.loginEditText.setText(state.login)
        binding.passwordEditText.setText(state.password)
    }

    private fun showToast(text: String) {
        Toast.makeText(
            requireContext(),
            text,
            Toast.LENGTH_SHORT
        ).show()
    }
}