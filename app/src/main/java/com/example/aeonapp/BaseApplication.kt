package com.example.aeonapp

import android.app.Application
import com.github.terrakok.cicerone.Cicerone

class BaseApplication : Application() {

    private val cicerone = Cicerone.create()
    val router get() = cicerone.router
    val navigatorHolder get() = cicerone.getNavigatorHolder()

    companion object {
        lateinit var instance: BaseApplication
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}