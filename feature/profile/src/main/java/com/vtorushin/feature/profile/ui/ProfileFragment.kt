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
        return binding.root
    }
}