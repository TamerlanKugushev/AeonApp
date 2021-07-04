package com.example.aeonapp.network

import com.example.aeonapp.data.models.LoginResponse
import com.example.aeonapp.data.models.PaymentsResponse
import io.reactivex.Single
import retrofit2.http.*

interface ApiService {

    companion object {
        private const val LOGIN = "api-test/login"
        private const val PAYMENTS = "api-test/payments"
    }

    @POST(LOGIN)
    @FormUrlEncoded
    fun loginUser(
        @Field("login") login: String,
        @Field("password") password: String,
    ): Single<LoginResponse>

    @GET(PAYMENTS)
    fun getPayments(
        @Query("token") token: String?
    ): Single<PaymentsResponse>
}