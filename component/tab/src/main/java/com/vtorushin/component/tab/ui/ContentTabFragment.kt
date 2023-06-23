package com.vtorushin.component.tab.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.vtorushin.component.tab.FragmentKeys.ARG_KEY
import com.vtorushin.component.tab.FragmentKeys.FIRST_SCREEN
import com.vtorushin.component.tab.FragmentKeys.SECOND_SCREEN
import com.vtorushin.component.tab.R
import com.vtorushin.component.tab.databinding.FragmentTabContentBinding
import com.vtorushin.component.tab.di.component

class ContentTabFragment : Fragment() {
    private val viewModel by lazy { component().viewModel() }
    private var screenKey: String? = null

    private val cicerone = Cicerone.create()
    private val router = cicerone.router
    private val navigatorHolder = cicerone.getNavigatorHolder()
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            screenKey = it.getString(ARG_KEY)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d("ViewModel", viewModel.toString())
        val binding = FragmentTabContentBinding.inflate(layoutInflater, container, false)
        if (savedInstanceState == null)
            viewModel.setStartState(router, screenKey?: throw IllegalArgumentException("Cant find screen key"))
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