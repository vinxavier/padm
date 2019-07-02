package br.edu.ufabc.padm.ebooklibrary

import android.app.Application
import android.content.Context
import android.util.Log

class App: Application() {
    companion object {

        lateinit var context: Context
            private set

    }


    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}