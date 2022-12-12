package com.aasif.weatherforecasetmvvm.data.provider

import android.content.Context


const val UNIT_SYSTEM = "UNIT_SYSTEM"
class UnitProviderImpl(context: Context): PreferenceProvider(context), UnitProvider {
    override fun getUnitSystem(): String {
        val unit =  preference.getString(UNIT_SYSTEM, "f")
        if(unit != null)
            return unit
        else
            return "f"
    }
}