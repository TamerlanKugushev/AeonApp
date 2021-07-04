package com.example.aeonapp.presentation.login

import android.annotation.SuppressLint
import android.util.Log
import com.example.aeonapp.BaseApplication
import com.example.aeonapp.Screens
import com.example.aeonapp.domain.LoginInteractor
import com.example.aeonapp.utils.BasePresenter
import com.github.terrakok.cicerone.Router
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class LoginPresenter : BasePresenter<LoginView>() {

    private val loginInteractor = LoginInteractor()
    private val router: Router = BaseApplication.instance.router

    @SuppressLint("CheckResult")
    fun loginUser(login: String, password: String) {
        loginInteractor.loginUser(login, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    router.newRootScreen(Screens.PaymentsScreen())
                },
                onError = {
                    getView()?.showError()
                    Log.e("LOGIN", it.message.toString())
                }
            ).addTo(viewCompositeDisposable)
    }
}