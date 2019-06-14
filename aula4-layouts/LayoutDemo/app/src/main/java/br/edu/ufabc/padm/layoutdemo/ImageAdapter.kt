package br.edu.ufabc.padm.layoutdemo

import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView

/**
 * Custom adapter to display image thumbnails
 */
class ImageAdapter : BaseAdapter() {
    // references to images (as resources)
    private val thumbnails = arrayOf(
            R.drawable.sample_2, R.drawable.sample_3, R.drawable.sample_4, R.drawable.sample_5,
            R.drawable.sample_6, R.drawable.sample_7, R.drawable.sample_0, R.drawable.sample_1,
            R.drawable.sample_2, R.drawable.sample_3, R.drawable.sample_4, R.drawable.sample_5,
            R.drawable.sample_6, R.drawable.sample_7, R.drawable.sample_0, R.drawable.sample_1,
            R.drawable.sample_2, R.drawable.sample_3, R.drawable.sample_4, R.drawable.sample_5,
            R.drawable.sample_6, R.drawable.sample_7)

    override fun getCount(): Int {
        return thumbnails.size
    }

    override fun getItem(position: Int): Any? {
        return null
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val imageView: ImageView

        if (convertView == null) {
            imageView = ImageView(App.context)
            imageView.layoutParams = ViewGroup.LayoutParams(
                    App.context.resources.getDimensionPixelSize(R.dimen.image_view_grid_width),
                    App.context.resources.getDimensionPixelSize(R.dimen.image_view_grid_width))
            imageView.scaleType = ImageView.ScaleType.CENTER_CROP
            imageView.setPadding(8, 8, 8, 8)
        } else
            imageView = convertView as ImageView

        imageView.setImageResource(thumbnails[position])

        return imageView
    }
}
