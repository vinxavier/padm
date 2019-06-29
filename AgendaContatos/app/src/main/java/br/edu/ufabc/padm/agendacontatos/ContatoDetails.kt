package br.edu.ufabc.padm.agendacontatos

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import br.edu.ufabc.padm.agendacontatos.model.ContatoDAO


class ContatoDetails : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.contato_details)
        showContato()
    }

    private fun showContato() {
        val pos = intent.extras?.getInt("contactPosition") ?: 0
        val contato = ContatoDAO.instance.getItemAt(pos)

        (findViewById<TextView>(R.id.nameLabel)).text = contato.nome
        (findViewById<TextView>(R.id.enderecoLabel)).text = contato.endereco
        (findViewById<TextView>(R.id.emailLabel)).text = contato.email
        (findViewById<TextView>(R.id.telefoneComercialLabel)).text = contato.telefoneComercial
        (findViewById<TextView>(R.id.telefoneResidencialLabel)).text = contato.telefoneResidencial
    }
}
