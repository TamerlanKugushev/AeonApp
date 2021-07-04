package com.example.aeonapp.data.models

data class PaymentsResponse(
    val success: Boolean,
    val response: List<PaymentInfo>
)

data class PaymentInfo(
    val desc: String,
    val amount: Double,
    val currency: String?,
    val created: Long,
)