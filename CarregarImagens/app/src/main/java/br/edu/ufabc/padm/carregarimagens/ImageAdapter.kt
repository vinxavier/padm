package br.edu.ufabc.padm.carregarimagens

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.image_item.view.*


class ImageAdapter:BaseAdapter {
    var uriList = ArrayList<Uri>()

    var context: Context? = null

    constructor(context: Context, uriList: ArrayList<Uri>){

        this.uriList= uriList
        this.context = context
    }



    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val image = this.uriList[position]
        var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var imageView = inflator.inflate(R.layout.image_item, null)
        imageView.image_container.setImageURI(image)

        return imageView
    }

    override fun getItem(position: Int): Any {
        return uriList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return uriList.size
    }
}