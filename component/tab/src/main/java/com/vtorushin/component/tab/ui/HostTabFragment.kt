package com.vtorushin.component.tab.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.ViewModelProvider
import com.vtorushin.component.tab.FragmentKeys.FIRST_SCREEN
import com.vtorushin.component.tab.FragmentKeys.SECOND_SCREEN
import com.vtorushin.component.tab.R
import com.vtorushin.component.tab.databinding.FragmentHostTabBinding
import com.vtorushin.component.tab.di.component
import com.vtorushin.component.tab.presentation.HostTabViewModel

class HostTabFragment : Fragment() {
    private val viewModel by lazy { ViewModelProvider(this)[HostTabViewModel::class.java] }
    private var binding: FragmentHostTabBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHostTabBinding.inflate(inflater, container, false)
        if (savedInstanceState == null)
            initStartState()
        listenBottomNavigation()
        handleBackPressed()
        return binding?.root
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        childFragmentManager.findFragmentByTag(viewModel.unActive)?.view?.visibility = View.INVISIBLE
    }

    override fun onResume() {
        super.onResume()
        callback.isEnabled = true
        //binding?.bottomNavigation?.selectedItemId = viewModel.getActiveId()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    private fun initStartState() {
        childFragmentManager.beginTransaction()
            .add(R.id.container, viewModel.first, FIRST_SCREEN)
            .add(R.id.container, viewModel.second, SECOND_SCREEN)
            .commit()
    }

    private fun listenBottomNavigation() {
        binding?.bottomNavigation?.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.profile -> viewModel.first(childFragmentManager)
                R.id.loan -> viewModel.second(childFragmentManager)
                else -> throw IllegalStateException("Unhandled navigation")
            }
            true
        }
    }

    private val callback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            val active = viewModel.getActive(childFragmentManager)
            if (!active.exit()) {
                isEnabled = false
                requireActivity().onBackPressedDispatcher.onBackPressed()
            }
        }
    }

    private fun handleBackPressed() {
        requireActivity().onBackPressedDispatcher.addCallback(callback)
    }
}