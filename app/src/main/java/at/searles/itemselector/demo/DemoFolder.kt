package at.searles.itemselector.demo

import android.widget.ImageView
import at.searles.itemselector.model.Folder
import at.searles.itemselector.model.Item

class DemoFolder(override val name: String, override val children: List<Item>): Folder {
    override val description: String = name
    override fun setImageInView(imageView: ImageView) {
        // ignore
    }

    override fun toString(): String {
        return name
    }
}