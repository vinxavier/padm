package br.edu.ufabc.padm.agendacontatos

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.ActionMode
import android.view.Menu
import android.view.MenuItem
import android.widget.AbsListView
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.edu.ufabc.padm.agendacontatos.model.ContatoDAO

class PegarContato:AppCompatActivity() {
    private lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pegue_um_contato)

        populateContatos()
    }

    private fun populateContatos() {
        listView = findViewById(R.id.list_contatos)

        listView.adapter = ContatoAdapter()
        listView.choiceMode = ListView.CHOICE_MODE_MULTIPLE_MODAL

        listView.setMultiChoiceModeListener(object : AbsListView.MultiChoiceModeListener{
            @SuppressLint("StringFormatInvalid")
            override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
                val checkedContacts = listView.checkedItemPositions
                val contatoAdapter = listView.adapter as ContatoAdapter
                val id = item?.itemId

                //fazer o track das posições
                val checkedPositions = IntArray(checkedContacts.size())
                var cont = 0

                for(i in 0 until contatoAdapter.count)
                    if (checkedContacts.get(i))
                        checkedPositions[cont++] = i

                if (id == R.id.action_confirm){
                    val returnIntent = Intent()
                    returnIntent.putExtra(MainActivity.REQUEST_RESULT, 42)
                    if(ContatoDAO.instance.size()!=0) {
                        returnIntent.putExtra("contact", ContatoDAO.instance.getItemAt(0))
                        setResult(Activity.RESULT_OK, returnIntent)
                        finish()
                        return true
                    }
                    else
                        return false
                }
                return false

            }

            @SuppressLint("StringFormatInvalid")
            override fun onItemCheckedStateChanged(mode: ActionMode?, position: Int, id: Long, checked: Boolean) {
                for (i in 0 until listView.getChildCount()) {
                    if (listView.checkedItemPositions[i]) {
                        listView.getChildAt(i).setBackgroundColor(Color.parseColor("#d3fdfa"))
                    } else {
                        listView.getChildAt(i).setBackgroundColor(Color.TRANSPARENT)
                    }
                }

            }

            override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                val inflater = mode?.menuInflater

                if (inflater != null) {
                    inflater.inflate(R.menu.pegar_contato_menu, menu)
                }
                if (mode != null) {
                    mode.title = resources.getString(R.string.preparando_para_enviar)
                }




                return true
            }

            override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                return false
            }

            override fun onDestroyActionMode(mode: ActionMode?) {
            }

        })

        listView.onItemClickListener = AdapterView.OnItemClickListener { parent, _, position, _ ->
            val returnIntent = Intent()
            //returnIntent.putExtra(MainActivity.REQUEST_RESULT, 42)
            if(ContatoDAO.instance.getItemAt(0)!=null) {

                println(ContatoDAO.instance.getItemAt(0))

                returnIntent.putExtra("contact", ContatoDAO.instance.getItemAt(0))
                setResult(Activity.RESULT_OK, returnIntent)
                finish()
            }
        }

    }
}