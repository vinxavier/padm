package br.edu.ufabc.padm.intentdemo

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class NoFlagActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_no_flag)

        findViewById<TextView>(R.id.task_id_message).text = getString(R.string.task_id, this.taskId)
    }
}
