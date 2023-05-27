package edu.skku.cs.pp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import edu.skku.cs.pp.category.Category
import edu.skku.cs.pp.category.CategoryAdapter
import edu.skku.cs.pp.constant.Names
import edu.skku.cs.pp.constant.Names.Companion.SEOUL_CULTURE
import edu.skku.cs.pp.constant.Names.Companion.SEOUL_GALLERY
import edu.skku.cs.pp.constant.Names.Companion.SEOUL_MUSEUM

class MainActivity : AppCompatActivity() {

    private val categoryNames = arrayListOf(SEOUL_MUSEUM, SEOUL_GALLERY, SEOUL_CULTURE)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val categories = ArrayList<Category>()
        for (name in categoryNames) {
            categories.add(Category(name))
        }

        val categoryAdapter = CategoryAdapter(this@MainActivity, categories)
        val listView = findViewById<ListView>(R.id.listView)
        listView.adapter = categoryAdapter
    }
}