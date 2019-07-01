package br.edu.ufabc.padm.agendacontatos

import android.app.Application
import android.content.Context
import android.util.Log

class App:Application() {
    companion object {

        lateinit var context: Context
            private set

        val CONTACT_EDIT_EXTRA_KEY = "isEditing"
        val CONTACT_POSITION_EXTRA_KEY: String? = "contactPosition"


    }


    override fun onCreate() {
        Log.d("LOGCONTATOS", "Entrou aqui no create do app")
        super.onCreate()
        context = applicationContext
    }


}