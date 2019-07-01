package br.edu.ufabc.padm.agendacontatos

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ActionMode
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import androidx.core.view.size
import br.edu.ufabc.padm.agendacontatos.model.ContatoDAO
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    private lateinit var addButton: FloatingActionButton

    companion object {
        val REQUEST_RESULT = "REQUEST_RESULT"
    }

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

                if (id == R.id.action_delete){

                    if(ContatoDAO.instance.removeAll(checkedPositions)){
                        if (mode != null) {
                            mode.finish()
                        } // close the CAB menu

                        Toast.makeText(this@MainActivity, getString(R.string.success_remove_contact),
                            Toast.LENGTH_LONG).show()

                    } else {
                        Toast.makeText(this@MainActivity, getString(R.string.failed_remove_contact),
                            Toast.LENGTH_LONG).show()
                    }
                    return true
                 }
                else if (id == R.id.action_edit){
                    val intent = Intent(App.context, ContatoInsertEdit::class.java)

                    intent.putExtra(App.CONTACT_EDIT_EXTRA_KEY, true)
                    intent.putExtra(App.CONTACT_POSITION_EXTRA_KEY, checkedPositions.get(0))
                    startActivity(intent)

                }
                else {
                    Log.e("MenuDemo", "Unknown operation")
                    return true
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
                if (mode != null) {
                    mode.title = getString(
                        R.string.context_menu_selected_count,
                        listView.checkedItemCount
                    )
                    if(listView.checkedItemCount==1)
                        mode.menu.findItem(R.id.action_edit).setVisible(true)
                    else
                        mode.menu.findItem(R.id.action_edit).setVisible(false)

                }


            }

            override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                val inflater = mode?.menuInflater

                if (inflater != null) {
                    inflater.inflate(R.menu.edite_delete_menu, menu)
                }
                if (mode != null) {
                    mode.title = resources.getString(R.string.context_menu_title)
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
            val intent = Intent(parent.context, ContatoDetails::class.java)

            intent.putExtra("contactPosition", position)
            startActivity(intent)
        }

        addButton.setOnClickListener{
            val intent = Intent(this, ContatoInsertEdit::class.java)

            startActivity(intent)
        }

    }

    override fun onResume() {
        super.onResume()

        (listView.adapter as BaseAdapter).notifyDataSetChanged()
    }
}
