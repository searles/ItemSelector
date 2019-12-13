package at.searles.itemselector.model

import android.widget.ImageView

interface Entry {
    val name: String
    val description: String
    fun setImageInView(imageView: ImageView)
}