package br.edu.ufabc.padm.intentdemo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        populateDemoList()
    }

    private fun populateDemoList() {
        val listView = findViewById<View>(R.id.demo_list) as ListView

        listView.adapter = ArrayAdapter(this,
                android.R.layout.simple_selectable_list_item,
                resources.getStringArray(R.array.demo_list))
        listView.onItemClickListener = DemoListClickListener()
    }

    private inner class DemoListClickListener : AdapterView.OnItemClickListener {

        override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
            when (position) {
                0 -> startActivity(Intent(this@MainActivity, SimpleExplicitIntent::class.java))
                1 -> startActivity(Intent(this@MainActivity, ExplicitIntentWithExtras::class.java))
                2 -> startActivity(Intent(this@MainActivity, ExplicitIntentWithFlags::class.java))
                3 -> startActivity(Intent(this@MainActivity, ImplicitIntentWithExtra::class.java))
                4 -> startActivity(Intent(this@MainActivity, ImplicitIntentWithResult::class.java))
                5 -> startActivity(Intent(this@MainActivity, ImplicitIntentWithAppChooser::class.java))
                6 -> startActivity(Intent(this@MainActivity, ImplicitIntentUnchecked::class.java))
                7 -> startActivity(Intent(this@MainActivity, ImplicitIntentFilterTest::class.java))
                else -> Log.w("MainActivity", "Unsupported demo")
            }
        }
    }
}
