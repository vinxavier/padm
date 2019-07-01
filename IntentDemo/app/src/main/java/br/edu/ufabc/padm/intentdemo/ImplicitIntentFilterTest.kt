package br.edu.ufabc.padm.intentdemo

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TableLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.edu.ufabc.padm.model.Contato


class ImplicitIntentFilterTest : AppCompatActivity() {
    internal val RESULT_CODE = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_implicit_intent_filter_test)
        registerHandlers()
    }

    private fun registerHandlers() {
        val pickButton = findViewById<View>(R.id.pick_button) as Button
        val self = this // closure [Wikipedia: http://bit.ly/18PwJB4]

        pickButton.setOnClickListener {
            val intent = Intent()

            intent.action = Intent.ACTION_PICK
            intent.type = "text/contact" // "invented" mime type

            if (intent.resolveActivity(packageManager) != null)
                startActivityForResult(intent, RESULT_CODE)
            else
                Toast.makeText(self, getString(R.string.intent_unavailable_message),
                        Toast.LENGTH_LONG).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        //val intent: Intent = getIntent()

        val contactFailureMessage = {
            Toast.makeText(
                    this,
                    getString(R.string.contact_failed_message),
                    Toast.LENGTH_LONG).show()
        }

        if (requestCode == 0 && resultCode == RESULT_OK) {
            println("Entrei aqui")
            var contact : Contato? = data?.getParcelableExtra("contact")
            val nome = findViewById<View>(R.id.contato_item_nome) as TextView
            val email = findViewById<View>(R.id.contato_item_email) as TextView
            val endereco = findViewById<View>(R.id.contato_item_endereco) as TextView
            val telComercial = findViewById<View>(R.id.contato_item_telcom) as TextView
            val telResidencial = findViewById<View>(R.id.contato_item_telres) as TextView
            val table = findViewById<View>(R.id.contact_show) as TableLayout

            if (contact!=null) {
                nome.text = contact.nome
                email.text = contact.email
                endereco.text = contact.endereco
                telComercial.text = contact.telefoneComercial
                telResidencial.text = contact.telefoneResidencial
                table.visibility = TableLayout.VISIBLE
                Toast.makeText(this, getString(R.string.contact_success_message), Toast.LENGTH_LONG).show()
            } else
                contactFailureMessage()

        } else {
            contactFailureMessage()
        }
    }
}
