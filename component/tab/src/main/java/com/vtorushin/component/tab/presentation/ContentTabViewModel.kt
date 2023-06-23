package com.vtorushin.component.tab.presentation

import androidx.lifecycle.ViewModel
import com.github.terrakok.cicerone.Router
import com.vtorushin.component.tab.FragmentKeys.FIRST_SCREEN
import com.vtorushin.component.tab.FragmentKeys.SECOND_SCREEN
import javax.inject.Inject

class ContentTabViewModel @Inject constructor(
    val provider: TabsScreenProvider
) : ViewModel() {
    fun setStartState(router: Router, screenKey: String) {
        router.newRootScreen(
            when (screenKey) {
                FIRST_SCREEN -> provider.getFirstScreen()
                SECOND_SCREEN -> provider.getSecondScreen()
                else -> throw IllegalStateException("Unknown fragment")
            }
        )
    }
}