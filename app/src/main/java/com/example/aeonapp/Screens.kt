@file:Suppress("FunctionName")


package com.example.aeonapp

import com.example.aeonapp.presentation.login.LoginFragment
import com.example.aeonapp.presentation.payments.PaymentsFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {

    fun LoginScreen() = FragmentScreen { LoginFragment.newInstance() }
    fun PaymentsScreen() = FragmentScreen { PaymentsFragment.newInstance() }
}