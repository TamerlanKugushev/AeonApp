package com.example.aeonapp.data.models

data class LoginResponse(
    val success: Boolean,
    val response: Token
) {

    data class Token(val token: String)
}