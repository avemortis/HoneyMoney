package com.vtorushin.feature.authoption.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vtorushin.feature.authoption.R
import com.vtorushin.feature.authoption.databinding.FragmentAuthOptionBinding
import com.vtorushin.feature.authoption.di.clear
import com.vtorushin.feature.authoption.di.component

class AuthOptionFragment : Fragment() {
    private val viewModel by lazy { component().viewModel() }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentAuthOptionBinding.inflate(inflater, container, false)
        binding.registrationOptionButton.setOnClickListener { viewModel.toRegistration() }
        binding.loginOptionButton.setOnClickListener { viewModel.toLogin() }
        return binding.root
    }

    override fun onDestroy() {
        clear()
        super.onDestroy()
    }
}