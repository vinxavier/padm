package br.edu.ufabc.todolist

import android.app.Application
import android.content.Context

class App : Application() {

    companion object {
        lateinit var context: Context
            private set

        // constants to be used as contracts in the app
        val INSERT_TASK_REQCODE = 0
        val TASK_POSITION_KEY = "taskPosition"
    }


    override fun onCreate() {
        super.onCreate()

        context = applicationContext
    }

}