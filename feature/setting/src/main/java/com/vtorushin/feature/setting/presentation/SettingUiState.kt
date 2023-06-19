package com.vtorushin.feature.setting.presentation

sealed interface SettingUiState {
    object Successes : SettingUiState
    object NameIsEmpty : SettingUiState
    object LastNameIsEmpty : SettingUiState
    data class SettingsState(val name: String, val lastName: String, val isRemember: Boolean) : SettingUiState
}