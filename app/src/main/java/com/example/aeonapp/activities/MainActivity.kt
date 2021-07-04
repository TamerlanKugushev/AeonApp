package com.example.aeonapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.aeonapp.BaseApplication
import com.example.aeonapp.R
import com.example.aeonapp.Screens
import com.example.aeonapp.data.repository.Repository
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator

class MainActivity : AppCompatActivity() {

    private val navigator = AppNavigator(this, R.id.fragmentContainer)
    private val navigatorHolder = BaseApplication.instance.navigatorHolder
    private val router: Router = BaseApplication.instance.router

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            if (Repository.isUserAuthorized()) {
                router.newRootScreen(Screens.PaymentsScreen())
            } else {
                router.newRootScreen(Screens.LoginScreen())
            }
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }
}