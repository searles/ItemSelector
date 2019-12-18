package at.searles.itemselector.demo

import android.widget.ImageView
import at.searles.itemselector.model.Folder
import at.searles.itemselector.model.Item

class DemoFolder(override val key: String, override val children: List<Item>): Folder {
    override val title: String = "title: $key"
    override val description: String = "description: $key"
    override fun setImageInView(imageView: ImageView) {
        // ignore
    }

    override fun toString(): String {
        return title
    }
}