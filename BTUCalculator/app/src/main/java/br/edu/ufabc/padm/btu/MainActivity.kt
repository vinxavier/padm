package br.edu.ufabc.padm.btu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CheckBox
import android.widget.CheckedTextView
import android.widget.SeekBar
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var pessoasText: TextView
    private lateinit var areaText: TextView
    private lateinit var btusText: TextView
    private lateinit var pessoasSeekBar: SeekBar
    private lateinit var areaSeekBar: SeekBar
    private lateinit var luzCheckBox: CheckBox

    private val BLIGHT = 800
    private val BDARK = 600

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        bindComponents()
        bindHandlers()
        init()
    }

    private fun bindComponents(){
        pessoasText = findViewById(R.id.pessoasText)
        areaText = findViewById(R.id.areaText)
        btusText = findViewById(R.id.btusValue)
        pessoasSeekBar = findViewById(R.id.pessoasSeekBar)
        areaSeekBar = findViewById(R.id.areaSeekBar)
        luzCheckBox = findViewById(R.id.luzCheckBox)
    }

    private fun init(){
        areaSeekBar.progress = 10
        pessoasSeekBar.progress = 3
    }

    fun updateBtus () {
        val area = areaSeekBar.progress
        val pessoas = pessoasSeekBar.progress-1

        if (luzCheckBox.isChecked)
            btusText.text = (BLIGHT * (area + pessoas)).toString()
        else
            btusText.text = (BDARK * (area + pessoas)).toString()
    }

    fun updateArea(progress: Int){
        areaText.text = "$progress m²"
        updateBtus()
    }

    fun updatePessoas(progress: Int){
        pessoasText.text = "$progress"
        updateBtus()
    }

    private fun bindHandlers(){
        areaSeekBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                updateArea(progress)
            }
            override fun onStartTrackingTouch(seekBar: SeekBar) {}

            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })
        pessoasSeekBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                updatePessoas(progress)
            }
            override fun onStartTrackingTouch(seekBar: SeekBar) {}

            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })
        luzCheckBox.setOnCheckedChangeListener { buttonView, isChecked ->
            updateBtus()
        }
    }


}
