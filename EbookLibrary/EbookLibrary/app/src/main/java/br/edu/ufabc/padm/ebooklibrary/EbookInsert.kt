package br.edu.ufabc.padm.ebooklibrary

import android.os.Bundle
import android.widget.EditText
import android.widget.RatingBar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.edu.ufabc.padm.ebooklibrary.model.Ebook
import br.edu.ufabc.padm.ebooklibrary.model.EbookDAO

class EbookInsert: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ebook_insert)
    }

    private fun handleInsert() {


        val titulo = (findViewById<EditText>(R.id.insert_titulo)).text.toString()
        val autor = (findViewById<EditText>(R.id.insert_autor)).text.toString()
        val isbn = (findViewById<EditText>(R.id.insert_isbn)).text.toString()
        val ano = (findViewById<EditText>(R.id.insert_ano)).text.toString()
        val editora = (findViewById<EditText>(R.id.insert_editora)).text.toString()
        val avaliacao =(findViewById<RatingBar>(R.id.insert_avaliacao)).rating
        val toString: (EditText) -> String  = {
            it.text.toString().trim()
        }
        val ebook= Ebook()

//        fun isValid(): Boolean {
//            return arrayOf(titulo, autor)
//                .map {
//                    if (it.toString().isEmpty()) {
//                        it.error = getString(R.string.required_field)
//                        false
//                    } else true
//                }
//                .fold(true) { a: Boolean, b: Boolean ->
//                    a && b
//                }
//        }

        ebook.titulo = titulo
        ebook.autor = autor
        ebook.ISBN = isbn
        ebook.ano = ano
        ebook.editora = editora
        ebook.avalicao = avaliacao

        EbookDAO.instance.add(ebook)

        Toast.makeText(this, getString(R.string.insert_status_ok), Toast.LENGTH_SHORT).show()
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
