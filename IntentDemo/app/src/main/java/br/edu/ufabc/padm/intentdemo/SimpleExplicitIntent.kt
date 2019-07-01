package br.edu.ufabc.padm.intentdemo

import android.os.Bundle

import android.view.Menu
import android.view.MenuItem

import androidx.appcompat.app.AppCompatActivity


class SimpleExplicitIntent : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple_explicit_intent)
    }
}
