package br.edu.ufabc.padm.wikistates

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.state_entry.view.*

/**
 * Custom adapter to display image thumbnails
 */
class ImageAdapter : BaseAdapter {
    // references to images (as resources)
    var estadoList = ArrayList<Estado>()
    var context: Context? = null

    constructor(context: Context, estadoList: ArrayList<Estado>){

        this.estadoList=estadoList
        this.context = context
    }

    override fun getCount(): Int {
        return estadoList.size
    }

    override fun getItem(position: Int): Any {
        return estadoList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val estado = this.estadoList[position]

        var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var estadoView = inflator.inflate(R.layout.state_entry, null)
        estadoView.imgUF.setImageResource(estado.image)
        estadoView.textUF.text=estado.name

        return estadoView
    }
}
