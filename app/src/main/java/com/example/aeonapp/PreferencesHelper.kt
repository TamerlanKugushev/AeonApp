package com.example.aeonapp

interface PreferencesHelper {

    fun getToken(): String?

    fun setToken(accessToken: String)

    fun removeToken()
}