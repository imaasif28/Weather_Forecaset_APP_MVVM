package com.aasif.weatherforecasetmvvm.ui.settings

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.aasif.weatherforecasetmvvm.R

class SettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.prefs)
    }
}