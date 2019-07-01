package br.edu.ufabc.padm.intentdemo

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class ExplicitIntentWithExtrasTarget : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explicit_intent_with_extras_target)
        processIntent()
    }

    private fun processIntent() {
        val nome = intent.getStringExtra("nome")
        val ra = intent.getStringExtra("ra")
        val message = findViewById<TextView>(R.id.intent_extra_message)

        message.text = getString(R.string.intent_extra_message, nome, ra)
    }
}
