package br.edu.ufabc.padm.agendacontatos

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.edu.ufabc.padm.agendacontatos.model.Contato
import br.edu.ufabc.padm.agendacontatos.model.ContatoDAO


class ContatoInsert : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("LOGCONTATO","Contato insert foi criada")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.contato_insert)
    }

    private fun handleInsert(){
        val nome = (findViewById<EditText>(R.id.insertName)).text.toString()
        val email = (findViewById<EditText>(R.id.insertEmail)).text.toString()
        val endereco = (findViewById<EditText>(R.id.insertEndereco)).text.toString()
        val telefoneComercial = (findViewById<EditText>(R.id.insertTelefoneComercial)).text.toString()
        val telefoneResidencial = (findViewById<EditText>(R.id.insertTelefoneResidencial)).text.toString()
        val contato = Contato()

        contato.nome = nome
        contato.email = email
        contato.endereco = endereco
        contato.telefoneComercial = telefoneComercial
        contato.telefoneResidencial = telefoneResidencial

        ContatoDAO.instance.add(contato)
        Toast.makeText(this, getString(R.string.insert_done), Toast.LENGTH_SHORT).show()
        finish()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.insert_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId


        return if (id == R.id.action_add) {
            handleInsert()
            true
        } else super.onOptionsItemSelected(item)

    }
}