package com.example.aeonapp.domain

import com.example.aeonapp.data.models.PaymentInfo
import com.example.aeonapp.data.repository.Repository
import io.reactivex.Single

class PaymentsInteractor {

    fun getPayments(): Single<List<PaymentInfo>> {
        return Repository.getPayments()
    }

}