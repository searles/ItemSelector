package at.searles.itemselector.demo

import android.widget.ImageView
import at.searles.itemselector.model.Item

class DemoItem(override val name: String): Item {
    override val description: String = name
    override fun setImageInView(imageView: ImageView) {
        // ignore
    }

    override fun toString(): String {
        return name
    }
}