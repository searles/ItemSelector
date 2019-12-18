package at.searles.itemselector.demo

import android.widget.ImageView
import at.searles.itemselector.model.Item

class DemoItem(override val key: String): Item {
    override val title: String = "title: $key"
    override val description: String = "description: $key"
    override fun setImageInView(imageView: ImageView) {
        // ignore
    }

    override fun toString(): String {
        return title
    }
}