package com.vtorushin.feature.setting.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.lifecycleScope
import com.vtorushin.feature.setting.R
import com.vtorushin.feature.setting.databinding.FragmentSettingBinding
import com.vtorushin.feature.setting.di.clearComponent
import com.vtorushin.feature.setting.di.component
import com.vtorushin.feature.setting.presentation.SettingUiState
import kotlinx.coroutines.launch

class SettingFragment : Fragment() {
    private val viewModel by lazy { component().viewModel() }
    private lateinit var binding: FragmentSettingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            if (it.getSerializable(LAUNCH_MODE_KEY)
                == SettingLaunchMode.WITH_SETTINGS_CLEARING
            ) {
                viewModel.clear()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingBinding.inflate(inflater, container, false)
        lifecycleScope.launch {
            viewModel.state.collect(this@SettingFragment::handleState)
        }
        binding.nameEditText.doAfterTextChanged { text -> viewModel.name = text.toString() }
        binding.lastNameEditText.doAfterTextChanged { text -> viewModel.lastName = text.toString() }
        binding.phoneNumberEditText.doAfterTextChanged { text -> viewModel.phoneNumber = text.toString() }
        binding.rememberMeCheckbox.setOnCheckedChangeListener { _, to ->
            viewModel.onRememberMeChanged(to)
        }
        binding.saveSettingsButton.setOnClickListener { viewModel.saveSettings() }
        return binding.root
    }

    private fun handleState(state: SettingUiState) {
        when (state) {
            SettingUiState.LastNameIsEmpty -> setErrorToLastName()
            SettingUiState.NameIsEmpty -> setErrorToName()
            SettingUiState.PhoneNumberIsEmpty -> setErrorToPhoneNumber()
            is SettingUiState.SettingsState -> setState(state)
            SettingUiState.Successes -> clearComponent()
        }
    }

    private fun setErrorToName() {
        binding.nameEditText.error = getString(R.string.cannot_be_empty)
    }

    private fun setErrorToLastName() {
        binding.lastNameEditText.error = getString(R.string.cannot_be_empty)
    }

    private fun setErrorToPhoneNumber() {
        binding.phoneNumberEditText.error = getString(R.string.cannot_be_empty)
    }

    private fun setState(state: SettingUiState.SettingsState) {
        binding.nameEditText.setText(state.name)
        binding.lastNameEditText.setText(state.lastName)
        binding.rememberMeCheckbox.isChecked = state.isRemember
    }

    companion object {
        private const val LAUNCH_MODE_KEY = "Launch mode key"

        @JvmStatic
        fun newInstance(mode: SettingLaunchMode) =
            SettingFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(LAUNCH_MODE_KEY, mode)
                }
            }
    }
}