package com.example.aeonapp

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

class AppPreferencesHelper(context: Context) : PreferencesHelper {

    companion object {
        private const val TOKEN_PREF_NAME = "Shared_Preferences_Demo"
        private const val PREF_KEY_ACCESS_TOKEN = "PREF_KEY_ACCESS_TOKEN"
    }

    private var mPrefs: SharedPreferences? = null


    init {
        mPrefs = context.getSharedPreferences(TOKEN_PREF_NAME, Context.MODE_PRIVATE)
    }

    override fun getToken(): String? {
        return mPrefs?.getString(PREF_KEY_ACCESS_TOKEN, null);

    }

    override fun setToken(accessToken: String) {
        mPrefs?.edit {
            putString(PREF_KEY_ACCESS_TOKEN,accessToken)
        }
    }

    override fun removeToken() {
        mPrefs?.edit {
            remove(PREF_KEY_ACCESS_TOKEN)
        }
    }
}