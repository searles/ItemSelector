package at.searles.itemselector.demo

import android.widget.ImageView
import at.searles.itemselector.model.Item

class DemoItem(override val key: String): Item {
    override val title: String = key
    override val description: String = title
    override fun setImageInView(imageView: ImageView) {
        // ignore
    }

    override fun toString(): String {
        return title
    }
}