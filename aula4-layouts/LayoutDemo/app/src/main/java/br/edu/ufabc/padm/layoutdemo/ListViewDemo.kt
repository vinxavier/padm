package br.edu.ufabc.padm.layoutdemo

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class ListViewDemo : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view_demo)
        populateListView()
    }

    private fun populateListView() {
        // retrieve the list layout
        val listView = findViewById<View>(R.id.color_list) as ListView

        // register the adapter
        listView.adapter = ArrayAdapter(App.context,
                android.R.layout.simple_list_item_1,
                resources.getStringArray(R.array.cities_list))
        // register handler for item click
        listView.onItemClickListener = AdapterView.OnItemClickListener { _, view, _, _ ->
            val city = "${(view as TextView).text.toString()} + , SP, Brazil".replace(' ', '+')
            val geoIntent: Intent

            geoIntent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=$city"))
            startActivity(geoIntent)
        }
    }
}
