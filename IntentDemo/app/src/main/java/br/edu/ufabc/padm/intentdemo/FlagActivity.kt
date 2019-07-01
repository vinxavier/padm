package br.edu.ufabc.padm.intentdemo

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class FlagActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flag)
        fillData()
    }

    private fun fillData() {
        val textView = findViewById<TextView>(R.id.task_id_message)

        textView.text = getString(R.string.task_id, this.taskId)
    }
}
