package com.example.aeonapp.presentation.payments

import com.example.aeonapp.data.models.PaymentInfo
import com.example.aeonapp.data.models.PaymentsResponse
import com.example.aeonapp.utils.BaseView

interface PaymentsView : BaseView {

    fun updatePayments(payments: List<PaymentInfo>)

    fun updateState(paymentsScreenStates: PaymentsScreenStates)

}