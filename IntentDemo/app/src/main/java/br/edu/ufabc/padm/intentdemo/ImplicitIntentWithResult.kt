package br.edu.ufabc.padm.intentdemo

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class ImplicitIntentWithResult : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_implicit_intent_with_result)

        findViewById<Button>(R.id.pick_button).setOnClickListener { handleClick() }
    }

    private fun handleClick() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)

        intent.type = "image/*"
        if (intent.resolveActivity(packageManager) != null)
            startActivityForResult(intent, 0)

        else
            Toast.makeText(
                    this,
                    getString(R.string.invalid_intent),
                    Toast.LENGTH_LONG).show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 0 && resultCode == RESULT_OK) {
            val photoURI : Uri? = data?.data
            val imageView = findViewById<ImageView>(R.id.photo_container)

            imageView.scaleType = ImageView.ScaleType.CENTER_CROP
            imageView.setImageURI(photoURI)

            if (photoURI == null)
                Toast.makeText(
                        this,
                        getString(R.string.no_photo_returned),
                        Toast.LENGTH_LONG).show()
        }
    }
}
