package com.example.aeonapp.data.repository

import com.example.aeonapp.AppPreferencesHelper
import com.example.aeonapp.BaseApplication
import com.example.aeonapp.data.models.LoginResponse
import com.example.aeonapp.data.models.PaymentInfo
import com.example.aeonapp.network.RetrofitHolder
import io.reactivex.Single

object Repository {

    private val apiService = RetrofitHolder.apiService
    private var prefsHelper = AppPreferencesHelper(BaseApplication.instance.baseContext)

    fun loginUser(login: String, password: String): Single<LoginResponse> {
        return apiService
            .loginUser(login = login.trim(), password = password.trim())
            .map { response ->
                prefsHelper.setToken(accessToken = response.response.token)
                response
            }
    }

    fun getPayments(): Single<List<PaymentInfo>> {
        return apiService
            .getPayments(prefsHelper.getToken())
            .map {
                it.response
            }
    }

    fun logout(): Unit {
        prefsHelper.removeToken()
    }

    fun isUserAuthorized(): Boolean {
        return prefsHelper.getToken() != null
    }

}