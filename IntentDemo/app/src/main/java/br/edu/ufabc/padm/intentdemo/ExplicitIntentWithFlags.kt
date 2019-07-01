package br.edu.ufabc.padm.intentdemo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class ExplicitIntentWithFlags : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explicit_intent_with_flags)
        fillContent()
        registerHandlers()
    }

    private fun fillContent() {
        val textView = findViewById<TextView>(R.id.task_id_message)

        textView.text = getString(R.string.task_id_message, this.taskId)
    }

    private fun registerHandlers() {
        val flagsButton = findViewById<Button>(R.id.activate_button_flags)
        val noFlagsButton = findViewById<Button>(R.id.activate_button_no_flags)

        flagsButton.setOnClickListener {
            val intent = Intent(this, FlagActivity::class.java)

            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
        noFlagsButton.setOnClickListener {
            val intent = Intent(this, NoFlagActivity::class.java)

            startActivity(intent)
        }
    }
}
