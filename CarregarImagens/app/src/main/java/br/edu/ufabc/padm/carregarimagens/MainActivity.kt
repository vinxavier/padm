package br.edu.ufabc.padm.carregarimagens

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    var adapter: ImageAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            handleClick()
        }
    }

    private fun handleClick() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)

        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)

        intent.type = "image/*"
        if (intent.resolveActivity(packageManager) != null)
            startActivityForResult(intent, 0)
        else
            Toast.makeText(
                this,
                getString(R.string.invalid_intent),
                Toast.LENGTH_LONG).show()
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 0 && resultCode == RESULT_OK) {

            val clipData = data?.clipData
            val uriList = ArrayList<Uri>()
            if (clipData != null) {
                for (i in 0 until clipData.itemCount)
                    uriList.add(clipData.getItemAt(i).uri)
                adapter = ImageAdapter(this,uriList)
                image_grid.adapter = adapter
            }

            else{
                Toast.makeText(
                    this,
                    getString(R.string.no_photo_returned),
                    Toast.LENGTH_LONG).show()
            }

        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
