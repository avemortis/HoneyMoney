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
import com.vtorushin.feature.registration.di.registrationComponent
import com.vtorushin.feature.registration.presentation.RegistrationError
import com.vtorushin.feature.registration.presentation.RegistrationUiState
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class RegistrationFragment : Fragment() {
    private val viewModel by lazy { registrationComponent().viewModel() }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        binding.loginEditText.setText(viewModel.login)
        binding.passwordEditText.setText(viewModel.password)
        binding.registrationButton.setOnClickListener { viewModel.register() }
        binding.loginEditText.doAfterTextChanged { text -> viewModel.login = text.toString() }
        binding.passwordEditText.doAfterTextChanged { text -> viewModel.password = text.toString() }
        subscribe()
        return binding.root
    }

    private fun subscribe() {
        lifecycleScope.launch {
            viewModel.state.collect {
                if (it is RegistrationUiState.Error) {
                    when (it.error) {
                        RegistrationError.USER_ALREADY_EXIST -> Toast.makeText(
                            requireContext(),
                            R.string.user_already_exist,
                            Toast.LENGTH_SHORT
                        ).show()
                        RegistrationError.NETWORK_ERROR -> Toast.makeText(
                            requireContext(),
                            R.string.network_error,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }
}