package com.example.aeonapp.domain

import com.example.aeonapp.data.models.LoginResponse
import com.example.aeonapp.data.repository.Repository
import io.reactivex.Single

class LoginInteractor {

    fun loginUser(login: String, password: String): Single<LoginResponse> {
        return Repository.loginUser(login, password)
    }
}