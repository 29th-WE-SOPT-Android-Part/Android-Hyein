package org.sopt.androidassingment

import android.content.Context
import android.content.SharedPreferences

object SOPTSharedpreferences {
    private const val STORAGE_KEY="USER_AUTH"
    private const val AUTO_LOGIN = "AUTO_LOGIN"

    fun getAutoLogin(context: SignInActivity): Boolean{
        val preferences = getPreference(context)
        return preferences.getBoolean(AUTO_LOGIN, false)
    }

    fun setAutoLogin(context: SignInActivity, value: Boolean){
        val preferences = getPreference(context)
        preferences.edit()
            .putBoolean(AUTO_LOGIN, value)
            .apply()
    }

    fun removeAutoLogin(context: Context){
        val preferences = getPreference(context)
        preferences.edit()
            .remove(AUTO_LOGIN)
            .apply()
    }

    fun clearStorage(context: Context){
        val preferences = getPreference(context)
        preferences.edit()
            .clear()
            .apply()
    }

    private fun getPreference(context: Context): SharedPreferences = context.getSharedPreferences(STORAGE_KEY, Context.MODE_PRIVATE)
}