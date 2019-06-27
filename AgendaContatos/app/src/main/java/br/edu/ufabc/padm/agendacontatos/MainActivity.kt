package br.edu.ufabc.padm.agendacontatos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.BaseAdapter
import android.widget.ListView
import br.edu.ufabc.padm.agendacontatos.model.Contato
import br.edu.ufabc.padm.agendacontatos.model.ContatoDAO

class MainActivity : AppCompatActivity() {

    private lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        populateContatos()
    }

    private fun populateContatos() {
        listView = findViewById(R.id.list_contatos)

        listView.adapter = ContatoAdapter()


    }

    override fun onResume() {
        super.onResume()

        (listView.adapter as BaseAdapter).notifyDataSetChanged()
    }
}
