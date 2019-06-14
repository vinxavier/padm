package br.edu.ufabc.imccalculator

import android.os.Bundle
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var pesoText: TextView
    private lateinit var alturaText: TextView
    private lateinit var imcText: TextView
    private lateinit var imcStatusText: TextView
    private lateinit var pesoSeek: SeekBar
    private lateinit var alturaSeek: SeekBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        bindComponents()
        bindSeekbarHandlers()
        init()
    }

    private fun bindComponents() {
        pesoText = findViewById(R.id.peso_text)
        alturaText = findViewById(R.id.altura_text)
        imcText = findViewById(R.id.imc_text)
        imcStatusText = findViewById(R.id.imc_status_text)
        pesoSeek = findViewById(R.id.peso_seek)
        alturaSeek = findViewById(R.id.altura_seek)
    }

    private fun init() {
        alturaSeek.progress = 170
        pesoSeek.progress = 60
    }

    fun updateImc() {
        val peso = pesoSeek.progress.toDouble()
        val altura = alturaSeek.progress / 100.0

        var imc = 0.0

        if (altura > 0.0)
            imc = peso / (altura * altura)

        imcText.text = "%.2f".format(imc)

        if(imc<17){
            imcStatusText.text = "Muito Abaixo do Peso"
        }
        else if(imc<18.5){
            imcStatusText.text = "Abaixo do Peso"
        }
        else if(imc<25){
            imcStatusText.text = "Peso Normal"
        }
        else if(imc<30){
            imcStatusText.text = "Acima do Peso"
        }
        else if(imc<35){
            imcStatusText.text = "Obesidade I"
        }
        else if(imc<40){
            imcStatusText.text = "Obesidade II(Servera)"
        }
        else if(imc>=40){
            imcStatusText.text = "Obesidade III(Mórbida)"
        }
    }



    fun updatePeso(progress: Int) {
        pesoText.text = "$progress kg"
        updateImc()
    }

    fun updateAltura(progress: Int) {
        alturaText.text = "${(progress / 100.0).toString()} m"
        updateImc()
    }

    private fun bindSeekbarHandlers() {
        pesoSeek.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(override fun onStartTrackingTouch(seekBar: SeekBar) {}

            override fun onStopTrackingTouch(seekBar: SeekBar) {}seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                updatePeso(progress)
            }


        })
        alturaSeek.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                updateAltura(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}

            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })
    }


}
