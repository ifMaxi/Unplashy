package com.maxidev.unplashy.ui.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maxidev.unplashy.data.preferences.SettingsDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val settingsPreferences : SettingsDataStore
): ViewModel() {

    val isDarkModeActivated =
        settingsPreferences.settingsPreferencesFlow.map {
            it.isDarkMode
        }

    fun toggleDarkMode(isDarkMode : Boolean) =
        viewModelScope.launch {
            settingsPreferences.updateIsDarkMode(isDarkMode)
        }
}