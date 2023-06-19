package com.vtorushin.feature.setting

import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.vtorushin.feature.setting.ui.SettingFragment
import com.vtorushin.feature.setting.ui.SettingLaunchMode

fun getSettingScreen(mode: SettingLaunchMode) = FragmentScreen { SettingFragment.newInstance(mode) }