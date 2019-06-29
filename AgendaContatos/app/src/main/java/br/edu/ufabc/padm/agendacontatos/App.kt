package br.edu.ufabc.padm.agendacontatos

import android.app.Application
import android.content.Context
import android.util.Log

class App:Application() {
    companion object {
        lateinit var context: Context
            private set
    }


    override fun onCreate() {
        Log.d("LOGCONTATOS", "Entrou aqui no create do app")
        super.onCreate()
        context = applicationContext
    }


}