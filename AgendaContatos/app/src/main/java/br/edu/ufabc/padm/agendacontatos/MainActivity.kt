package br.edu.ufabc.padm.agendacontatos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.BaseAdapter
import android.widget.ListView
import br.edu.ufabc.padm.agendacontatos.model.Contato
import br.edu.ufabc.padm.agendacontatos.model.ContatoDAO
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    private lateinit var addButton: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("LOGCONTATOS", "Entrou aqui no create da main")
        addButton = findViewById(R.id.floating_button_add)
        populateContatos()

    }

    private fun populateContatos() {
        listView = findViewById(R.id.list_contatos)

        listView.adapter = ContatoAdapter()

        listView.onItemClickListener = AdapterView.OnItemClickListener { parent, _, position, _ ->
            val intent = Intent(parent.context, ContatoDetails::class.java)

            intent.putExtra("contactPosition", position)
            startActivity(intent)
        }

        addButton.setOnClickListener{
            val intent = Intent(this, ContatoInsert::class.java)

            startActivity(intent)
        }

    }

    override fun onResume() {
        super.onResume()

        (listView.adapter as BaseAdapter).notifyDataSetChanged()
    }
}
