package br.edu.ufabc.padm.intentdemo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity


class ImplicitIntentWithAppChooser : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_implicit_intent_with_app_chooser)
        registerHandlers()
    }

    private fun registerHandlers() {
        val sendButton = findViewById<Button>(R.id.send_button)

        sendButton.setOnClickListener {
            val sendIntent = Intent()
            val textBox = findViewById<EditText>(R.id.text_box)
            val title = getString(R.string.chooser_title)
            val chooserIntent = Intent.createChooser(sendIntent, title)

            sendIntent.action = Intent.ACTION_SEND
            sendIntent.putExtra(Intent.EXTRA_TEXT, textBox.text.toString())
            sendIntent.type = "text/plain"

            if (sendIntent.resolveActivity(packageManager) != null)
                startActivity(chooserIntent)
        }
    }
}
