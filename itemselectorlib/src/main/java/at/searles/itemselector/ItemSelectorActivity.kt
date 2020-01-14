package at.searles.itemselector

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import at.searles.itemselector.model.Folder
import at.searles.itemselector.model.FolderModel
import at.searles.itemselector.model.FoldersHolder
import at.searles.itemselector.model.Item

/**
 * Similar to Storage, but only to select items from a given folder structure.
 * No selection needed.
 */
class ItemSelectorActivity : AppCompatActivity(), FolderAdapter.Listener {

    private val recyclerView: RecyclerView by lazy {
        findViewById<RecyclerView>(R.id.contentRecyclerView).apply {
            setHasFixedSize(true)
        }
    }

    private lateinit var adapter: FolderAdapter
    private lateinit var model: FolderModel
    private lateinit var mergeMenu: MenuItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.demo_selector_activity_main)

        // set up data
        createModel()

        // set up data structures for viewing items
        adapter = FolderAdapter(this, model).apply {
            listener = this@ItemSelectorActivity
        }

        // and now for the recycler view
        with(recyclerView) {
            layoutManager = LinearLayoutManager(context)
            adapter = this@ItemSelectorActivity.adapter
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        }
    }

    private fun createModel() {
        val clazzName = intent.getStringExtra(initializerClassNameKey)!!

        @Suppress("UNCHECKED_CAST")
        val clazz = Class.forName(clazzName) as Class<FoldersHolder>
        val ctor = clazz.getConstructor(Context::class.java)

        val initializer = ctor.newInstance(this)

        model = FolderModel(initializer.folders)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        //TODO: Save which folders are open.
    }

    override fun onSaveInstanceState(outState: Bundle) {
        // TODO: Restore open folders
        super.onSaveInstanceState(outState)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.item_selector_main_menu, menu)
        mergeMenu = menu!!.findItem(R.id.mergeMenuItem)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.mergeMenuItem -> {
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun itemClicked(folder: Folder, item: Item) {
        val intent = Intent().apply {
            putExtra(folderKey, folder.key)
            putExtra(itemKey, item.key)
            putExtra(mergeKey, mergeMenu.isChecked)
        }

        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    companion object {
        const val initializerClassNameKey = "initializerClassName"
        const val folderKey = "folder"
        const val itemKey = "item"
        const val mergeKey = "mergeWithExisting"
    }

}
