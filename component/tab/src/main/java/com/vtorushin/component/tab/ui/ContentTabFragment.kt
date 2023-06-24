package com.vtorushin.component.tab.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.vtorushin.component.tab.FragmentKeys.ARG_KEY
import com.vtorushin.component.tab.FragmentKeys.FIRST_SCREEN
import com.vtorushin.component.tab.FragmentKeys.SECOND_SCREEN
import com.vtorushin.component.tab.R
import com.vtorushin.component.tab.databinding.FragmentTabContentBinding
import com.vtorushin.component.tab.di.component

class ContentTabFragment : Fragment() {
    private val screenKey by lazy { arguments?.getString(ARG_KEY) }
    private val viewModel by lazy { component().viewModel() }

    private val cicerone by lazy { when(screenKey) {
        FIRST_SCREEN -> component().firstCicerone()
        SECOND_SCREEN -> component().secondCicerone()
        else -> throw IllegalStateException()
    } }
    private val router by lazy { cicerone.router }
    private val navigatorHolder by lazy { cicerone.getNavigatorHolder() }
    private val navigator by lazy {
        AppNavigator(
            requireActivity(),
            R.id.tab_content,
            childFragmentManager
        )
    }

    fun exit(): Boolean {
        return if (childFragmentManager.backStackEntryCount > 0) {
            router.exit()
            true
        } else
            false
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentTabContentBinding.inflate(layoutInflater, container, false)
        if (savedInstanceState == null)
            viewModel.setStartState(
                router,
                screenKey ?: throw IllegalArgumentException("Cant find screen key")
            )
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    companion object {
        @JvmStatic
        fun newInstance(screeKey: String) =
            ContentTabFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_KEY, screeKey)
                }
            }
    }
}