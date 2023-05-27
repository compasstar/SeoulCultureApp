package edu.skku.cs.pp.category

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.TextView
import edu.skku.cs.pp.R
import edu.skku.cs.pp.constant.Names
import edu.skku.cs.pp.constant.Names.Companion.SEOUL_CULTURE
import edu.skku.cs.pp.constant.Names.Companion.SEOUL_GALLERY
import edu.skku.cs.pp.constant.Names.Companion.SEOUL_MUSEUM
import edu.skku.cs.pp.culture.CultureMain
import edu.skku.cs.pp.gallery.GalleryMain
import edu.skku.cs.pp.museum.MuseumMain

class CategoryAdapter(val context: Context, val data: ArrayList<Category>) : BaseAdapter() {

    override fun getCount(): Int {
        return data.size
    }

    override fun getItem(p0: Int): Any {
        return data[p0]
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val generatedView = inflater.inflate(R.layout.category, null)

        val btnCategory = generatedView.findViewById<Button>(R.id.btnCategory)
        btnCategory.text = data[p0].name

        btnCategory.setOnClickListener {
            selectActivity(btnCategory)
        }

        return generatedView
    }

    private fun selectActivity(btnCategory: Button) {
        if (btnCategory.text.equals(SEOUL_MUSEUM)) {
            val intentMuseum = Intent(context, MuseumMain::class.java)
            context.startActivity(intentMuseum)
        } else if (btnCategory.text.equals(SEOUL_GALLERY)) {
            val intentGallery = Intent(context, GalleryMain::class.java)
            context.startActivity(intentGallery)
        } else if (btnCategory.text.equals(SEOUL_CULTURE)) {
            val intentCulture = Intent(context, CultureMain::class.java)
            context.startActivity(intentCulture)
        }
    }
}