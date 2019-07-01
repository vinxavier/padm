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


class ContatoInsertEdit : AppCompatActivity() {

    private lateinit var contatoForm: ContatoForm

    private val isEditing: Boolean
        get() = intent.extras?.getBoolean(App.CONTACT_EDIT_EXTRA_KEY) ?: false


    override fun onStart() {
        super.onStart()

        if (isEditing) {
            setTitle(R.string.title_activity_contato_edit)
            fillForm()
        } else
            setTitle(R.string.title_activity_contato_insert)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("LOGCONTATO","Contato insert foi criada")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.contato_insert)
        contatoForm = ContatoForm()
    }

    private fun handleEdit() {
        if (contatoForm.isValid()) {
            val position = intent.extras?.getInt(App.CONTACT_EDIT_EXTRA_KEY) ?: 0

            ContatoDAO.instance.update(position, contatoForm.toContato())
            Toast.makeText(this, getString(R.string.edit_status_ok), Toast.LENGTH_LONG).show()
            finish()
        } else
            Toast.makeText(this, getString(R.string.edit_status_error), Toast.LENGTH_LONG).show()
    }

    private fun handleInsert(){

        if (contatoForm.isValid()) {
            ContatoDAO.instance.add(contatoForm.toContato())

            Toast.makeText(this, getString(R.string.insert_status_ok), Toast.LENGTH_LONG).show()
            finish()
        } else
            Toast.makeText(this, getString(R.string.insert_status_error), Toast.LENGTH_LONG).show()
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


        if (id == R.id.action_add) {
            if (isEditing)
                handleEdit()
            else
                handleInsert()

        }
        return super.onOptionsItemSelected(item)

    }

    private inner class ContatoForm {
        private val nome: EditText
        private val email: EditText
        private val endereco: EditText
        private val telefoneComercial: EditText
        private val telefoneResidencial: EditText
        private val toString: (EditText) -> String  = {
            it.text.toString().trim()
        }

        init {
            nome = findViewById<EditText>(R.id.insertName)
            email = findViewById<EditText>(R.id.insertEmail)
            endereco = findViewById<EditText>(R.id.insertEndereco)
            telefoneComercial = findViewById<EditText>(R.id.insertTelefoneComercial)
            telefoneResidencial = findViewById<EditText>(R.id.insertTelefoneResidencial)
        }

        fun isValid(): Boolean {
            return arrayOf(nome, email, endereco)
                .map {
                    if (toString(it).isEmpty()) {
                        it.error = getString(R.string.required_field)
                        false
                    } else true
                }
                .fold(true) { a: Boolean, b: Boolean ->
                    a && b
                }
        }

        fun fill(contato: Contato) {
            nome.setText(contato.nome)
            email.setText(contato.email)
            endereco.setText(contato.endereco)
            telefoneComercial.setText(contato.telefoneComercial)
            telefoneResidencial.setText(contato.telefoneResidencial)
        }

        fun toContato(): Contato {
            val contato = Contato(
                nome = toString(nome),
                email = toString(email),
                endereco = toString(endereco),
                telefoneResidencial = toString(telefoneResidencial),
                telefoneComercial = toString(telefoneComercial))
            return contato
        }
    }


    private fun fillForm() {
        val position = intent.extras?.getInt(App.CONTACT_POSITION_EXTRA_KEY) ?: 0
        val contato = ContatoDAO.instance.getItemAt(position)

        contatoForm.fill(contato)
    }
}