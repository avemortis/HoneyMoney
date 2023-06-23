package com.vtorushin.component.tab

import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.vtorushin.component.tab.ui.HostTabFragment

fun getTabScreen() = FragmentScreen { HostTabFragment() }