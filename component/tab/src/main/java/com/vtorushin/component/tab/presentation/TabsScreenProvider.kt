package com.vtorushin.component.tab.presentation

import com.github.terrakok.cicerone.androidx.FragmentScreen

interface TabsScreenProvider {
    fun getLoansScreen(): FragmentScreen
    fun getProfileScreen(): FragmentScreen
}