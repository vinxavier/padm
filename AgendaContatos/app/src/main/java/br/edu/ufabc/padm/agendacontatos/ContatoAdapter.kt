package br.edu.ufabc.padm.agendacontatos

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import br.edu.ufabc.padm.agendacontatos.model.ContatoDAO

class ContatoAdapter:BaseAdapter() {
    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return ContatoDAO.instance.size()
    }

    override fun getItem(position: Int): Any {
        return  ContatoDAO.instance.getItemAt(position)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = App.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var localView = convertView?: inflater.inflate(R.layout.contato_item_list, null)
        val contato = ContatoDAO.instance.getItemAt(position)
        val nome = localView.findViewById<TextView>(R.id.nameLabel)
        val phoneNumber = localView.findViewById<TextView>(R.id.phoneLabel)

        nome.text = contato.nome
        phoneNumber.text = contato.telefoneResidencial

        return localView
    }
}