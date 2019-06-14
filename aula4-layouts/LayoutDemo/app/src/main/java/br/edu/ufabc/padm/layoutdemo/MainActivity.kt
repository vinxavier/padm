package br.edu.ufabc.padm.layoutdemo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        populateLayoutChoices()
    }

    private fun populateLayoutChoices() {
        val spinner = findViewById<View>(R.id.layout_chooser) as Spinner
        val adapter = ArrayAdapter.createFromResource(this,
                R.array.layout_array, android.R.layout.simple_spinner_item)

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
        spinner.onItemSelectedListener = this
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
        val choice = parent.getItemAtPosition(position).toString()
        var intent: Intent? = null

        if (choice == "LinearLayout")
            intent = Intent(this, LinearLayoutDemo::class.java)
        else if (choice == "RelativeLayout")
            intent = Intent(this, RelativeLayoutDemo::class.java)
        else if (choice == "ConstraintLayout")
            intent = Intent(this, ConstraintLayoutDemo::class.java)
        else if (choice == "ListView")
            intent = Intent(this, ListViewDemo::class.java)
        else if (choice == "GridView")
            intent = Intent(this, GridViewDemo::class.java)

        intent?.let { startActivity(it) } ?: Log.e("MainActivity", "No option has been selected")
    }

    override fun onNothingSelected(parent: AdapterView<*>) {
        Log.d("TESTING", "no item has been selected")
    }
}
