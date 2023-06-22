package com.vtorushin.component.tab.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.terrakok.cicerone.*
import com.vtorushin.component.tab.FragmentKeys
import com.vtorushin.component.tab.FragmentKeys.PROFILE_TAG
import com.vtorushin.component.tab.R
import com.vtorushin.component.tab.databinding.FragmentTabBinding
import com.vtorushin.component.tab.di.component

class TabFragment : Fragment() {
    private val viewModel by lazy { component().viewModel() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentTabBinding.inflate(inflater, container, false)
        if (savedInstanceState == null) {
            childFragmentManager.beginTransaction().add(R.id.container, viewModel.loanFragment,
                FragmentKeys.LOAN_TAG
            )
                .add(R.id.container, viewModel.profileFragment, PROFILE_TAG)
                .commitNow()
            binding.bottomNavigation.selectedItemId = R.id.profile
        }
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.loan -> {
                    viewModel.loan(childFragmentManager)
                }
                R.id.profile -> {
                    viewModel.profile(childFragmentManager)
                }
                else -> {
                    throw IllegalStateException("Unhandled navigation")
                }
            }
            true
        }
        return binding.root
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        childFragmentManager.findFragmentByTag(viewModel.toHide)?.view?.visibility = View.INVISIBLE
    }
}