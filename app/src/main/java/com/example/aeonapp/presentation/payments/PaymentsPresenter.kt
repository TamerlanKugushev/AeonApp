package com.example.aeonapp.presentation.payments

import android.util.Log
import com.example.aeonapp.BaseApplication
import com.example.aeonapp.Screens
import com.example.aeonapp.data.models.PaymentInfo
import com.example.aeonapp.data.models.PaymentsResponse
import com.example.aeonapp.domain.PaymentsInteractor
import com.example.aeonapp.utils.BasePresenter
import com.github.terrakok.cicerone.Router
import com.jakewharton.rxrelay2.BehaviorRelay
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class PaymentsPresenter : BasePresenter<PaymentsView>() {

    private val paymentsInteractor = PaymentsInteractor()
    private val paymentsSubject = BehaviorRelay.create<List<PaymentInfo>>()
    private var paymentsScreenStates = PaymentsScreenStates.START
    private val router: Router = BaseApplication.instance.router

    init {
        loadAllTasks()
    }

    override fun bindView(view: PaymentsView) {
        super.bindView(view)
        subscribePayments()
    }

    fun navigateToLoginScreen() {
        router.newRootScreen(Screens.LoginScreen())
    }

    private fun loadAllTasks() {
        paymentsInteractor.getPayments()
            .subscribeOn(Schedulers.io())
            .subscribeBy(
                onSuccess = {
                    paymentsSubject.accept(it)
                },
                onError = {
                    Log.e("PAYMENTS", it.message.toString())
                }
            ).addTo(dataCompositeDisposable)
    }

    private fun subscribePayments() {
        paymentsSubject.hide()
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                paymentsScreenStates = PaymentsScreenStates.LOADING
                getView()?.updateState(paymentsScreenStates)
            }
            .subscribeBy(
                onNext = {
                    paymentsScreenStates = PaymentsScreenStates.CONTENT
                    getView()?.updateState(paymentsScreenStates)
                    getView()?.updatePayments(it)
                },
                onError = {
                    Log.e("PAYMENTS", it.message.toString())
                }
            ).addTo(viewCompositeDisposable)
    }
}