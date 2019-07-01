package br.edu.ufabc.padm.intentdemo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity


class ExplicitIntentWithExtras : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explicit_intent_with_extras)

        findViewById<Button>(R.id.intent_extra_send).setOnClickListener { handleSend() };
    }

    private fun handleSend() {
        val nomeField = findViewById<EditText>(R.id.intent_extra_nome)
        val raField = findViewById<EditText>(R.id.intent_extra_ra)
        var intent = Intent(this, ExplicitIntentWithExtrasTarget::class.java)

        if (nomeField.text.trim().length == 0)
            nomeField.error = getString(R.string.empty_field)
        else if (raField.text.trim().length == 0)
            raField.error = getString(R.string.empty_field)
        else {
            intent.putExtra("nome", nomeField.text.toString())
            intent.putExtra("ra", raField.text.toString())
            startActivity(intent)
        }
    }
}
