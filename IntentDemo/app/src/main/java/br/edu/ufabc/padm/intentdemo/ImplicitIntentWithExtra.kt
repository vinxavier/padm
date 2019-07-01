package br.edu.ufabc.padm.intentdemo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity


class ImplicitIntentWithExtra : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_implicit_intent_with_extra)
        registerHandlers()
    }

    private fun registerHandlers() {
        val sendButton = findViewById<Button>(R.id.send_button)

        sendButton.setOnClickListener {
            val intent = Intent()
            val textBox = findViewById<EditText>(R.id.text_box)

            intent.action = Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_TEXT, textBox.text.toString())
            intent.type = "text/plain"

            if (intent.resolveActivity(packageManager) != null)
                startActivity(intent)
        }
    }
}
