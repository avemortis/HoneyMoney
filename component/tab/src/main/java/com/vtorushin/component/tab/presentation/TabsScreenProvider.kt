package com.vtorushin.component.tab.presentation

import com.github.terrakok.cicerone.androidx.FragmentScreen

interface TabsScreenProvider {
    fun getFirstScreen(): FragmentScreen
    fun getSecondScreen(): FragmentScreen
}