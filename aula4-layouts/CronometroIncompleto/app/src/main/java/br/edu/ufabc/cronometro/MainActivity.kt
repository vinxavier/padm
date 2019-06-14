package br.edu.ufabc.cronometro

import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var timeElapsedText: TextView
    private lateinit var start_pause_button: Button
    private lateinit var reset_button: Button
    private var timeElapsed: Long = 0

    val TIMER_SPEED: Long = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()

        bindControls()
        runTimer()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        timeElapsed = savedInstanceState.getLong("timeElapsed")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putLong("timeElapsed", timeElapsed)
    }

    private fun bindControls() {
        timeElapsedText = findViewById(R.id.time_elapsed)
        start_pause_button = findViewById(R.id.start_pause_button)
        reset_button = findViewById(R.id.reset_button)
    }

    private fun reset() {
        timeElapsed = 0
    }

    private fun updateTime() {
        val hours = timeElapsed / 3600
        val minutes = timeElapsed % 3600 / 60
        val seconds = timeElapsed % 60

        timeElapsedText.setText(String.format("%02d:%02d:%02d", hours, minutes, seconds))
    }

    private fun runTimer() {
        val handler = Handler()

        start_pause_button.setOnClickListener {
            if(start_pause_button.text==getString(R.string.start))
                start_pause_button.text=getString(R.string.pause)
            else
                start_pause_button.text=getString(R.string.start)
        }

        reset_button.setOnClickListener {
            timeElapsed=0
        }

        handler.post(object : Runnable {
            override fun run() {
                if(start_pause_button.text==getString(R.string.pause))
                    timeElapsed++
                updateTime()
                handler.postDelayed(this, TIMER_SPEED)
            }
        })
    }
}
