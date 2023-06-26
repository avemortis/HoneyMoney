package com.vtorushin.feature.profile.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vtorushin.feature.profile.R
import com.vtorushin.feature.profile.databinding.FragmentProfileBinding
import com.vtorushin.feature.profile.di.component

class ProfileFragment : Fragment() {
    private val viewModel by lazy { component().viewModel() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentProfileBinding.inflate(inflater, container, false)
        binding.profileName.text = viewModel.name
        binding.profileLastName.text = viewModel.lastName
        binding.profilePhoneNumber.text = viewModel.phone
        binding.showOnBoardingButton.setOnClickListener { viewModel.onBoarding() }
        return binding.root
    }
}