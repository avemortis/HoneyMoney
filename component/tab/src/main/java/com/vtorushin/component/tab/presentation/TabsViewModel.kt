package com.vtorushin.component.tab.presentation

import android.view.View
import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import com.vtorushin.component.tab.FragmentKeys.LOAN_TAG
import com.vtorushin.component.tab.FragmentKeys.PROFILE_TAG
import javax.inject.Inject

class TabsViewModel @Inject constructor(
    val provider: TabsScreenProvider
) : ViewModel() {
    val loanFragment = provider.getLoansScreen().createFragment(FragmentFactory())
    val profileFragment = provider.getProfileScreen().createFragment(FragmentFactory())
    var toHide = LOAN_TAG

    fun loan(fm: FragmentManager) {
        toHide = PROFILE_TAG
        fm.findFragmentByTag(LOAN_TAG)?.view?.visibility = View.VISIBLE
        fm.findFragmentByTag(PROFILE_TAG)?.view?.visibility = View.INVISIBLE
    }
        //localCicerone.router.replaceScreen(provider.getLoansScreen())

    fun profile(fm: FragmentManager) {
        toHide = LOAN_TAG
        fm.findFragmentByTag(PROFILE_TAG)?.view?.visibility = View.VISIBLE
        fm.findFragmentByTag(LOAN_TAG)?.view?.visibility = View.INVISIBLE
    }
        //localCicerone.router.replaceScreen(provider.getProfileScreen())
}