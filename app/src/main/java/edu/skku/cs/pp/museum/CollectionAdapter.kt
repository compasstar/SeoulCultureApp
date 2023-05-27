package edu.skku.cs.pp.museum

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import edu.skku.cs.pp.R

class CollectionAdapter(val context: Context, val data: ArrayList<Art.Information.ArtInfo>): BaseAdapter() {

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
        val generatedView = inflater.inflate(R.layout.art, null)

        val thumbnail = generatedView.findViewById<ImageView>(R.id.imageViewThumbnail)
        val name = generatedView.findViewById<TextView>(R.id.textViewArtName)
        val director = generatedView.findViewById<TextView>(R.id.textViewArtDirector)
        val year = generatedView.findViewById<TextView>(R.id.textViewArtYear)

        name.text = data[p0].prdct_nm_korean
        director.text = data[p0].writr_nm
        year.text = data[p0].mnfct_year

        Glide.with(context).load(data[p0].thumb_image).into(thumbnail)

        return generatedView
    }
}