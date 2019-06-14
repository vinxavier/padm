package br.edu.ufabc.padm.uimodeldemo

import android.content.res.Configuration
import android.os.Bundle
import android.util.TypedValue
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val orientation = resources.configuration.orientation

        super.onCreate(savedInstanceState)
        // inject a landscape (declarative) layout
        if (orientation == Configuration.ORIENTATION_LANDSCAPE)
            setContentView(R.layout.activity_main)
        else if (orientation == Configuration.ORIENTATION_PORTRAIT)
            setContentView(createLayout())// inject a portrait (imperative) layout
    }

    /**
     * Programmatically create a portrait layout
     * @return the layout as a ViewGroup
     */
    private fun createLayout(): ViewGroup {
        val root = RelativeLayout(this)
        val text = TextView(this)

        root.layoutParams = RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT)
        text.setText(R.string.portrait_message)
        text.setTextSize(TypedValue.COMPLEX_UNIT_SP, 40f)
        root.addView(text)

        return root
    }
}
