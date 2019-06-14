package br.edu.ufabc.padm.layoutdemo

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.GridView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class GridViewDemo : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grid_view_demo)

        populateGrid()
    }

    private fun populateGrid() {
        val gridView = findViewById<View>(R.id.base_grid) as GridView

        gridView.adapter = ImageAdapter()
        gridView.onItemClickListener = AdapterView.OnItemClickListener { _, view, i, _ ->
            Toast.makeText(view.context,
                    String.format(getString(R.string.grid_item_clicked, i)),
                    Toast.LENGTH_LONG).show()
        }
    }
}
