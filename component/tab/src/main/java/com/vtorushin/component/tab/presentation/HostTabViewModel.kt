package com.vtorushin.component.tab.presentation

import android.view.View
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import com.vtorushin.component.tab.FragmentKeys.FIRST_SCREEN
import com.vtorushin.component.tab.FragmentKeys.SECOND_SCREEN
import com.vtorushin.component.tab.ui.ContentTabFragment

class HostTabViewModel : ViewModel() {
    var unActive = SECOND_SCREEN

    val first = ContentTabFragment.newInstance(FIRST_SCREEN)
    val second = ContentTabFragment.newInstance(SECOND_SCREEN)

    fun first(fm: FragmentManager) {
        unActive = SECOND_SCREEN
        fm.findFragmentByTag(SECOND_SCREEN)?.view?.visibility = View.INVISIBLE
        fm.findFragmentByTag(FIRST_SCREEN)?.view?.visibility = View.VISIBLE
    }

    fun second(fm: FragmentManager) {
        unActive = FIRST_SCREEN
        fm.findFragmentByTag(SECOND_SCREEN)?.view?.visibility = View.VISIBLE
        fm.findFragmentByTag(FIRST_SCREEN)?.view?.visibility = View.INVISIBLE
    }

    fun getActive(fm: FragmentManager) = when(unActive) {
        FIRST_SCREEN -> fm.findFragmentByTag(SECOND_SCREEN) as ContentTabFragment
        SECOND_SCREEN -> fm.findFragmentByTag(FIRST_SCREEN) as ContentTabFragment
        else -> {throw IllegalStateException("Unknown fragment")}
    }
}