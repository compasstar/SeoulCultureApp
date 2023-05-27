package edu.skku.cs.pp.gallery

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import edu.skku.cs.pp.R

class GalleryAdapter(val context: Context, val data:ArrayList<Gallery.GalleryInformation.GalleryInfo>): BaseAdapter() {

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
        val generatedView = inflater.inflate(R.layout.city, null)

        val name = generatedView.findViewById<TextView>(R.id.textViewCityName)
        val year = generatedView.findViewById<TextView>(R.id.textViewCityYear)
        val address1 = generatedView.findViewById<TextView>(R.id.textViewCityAddress)
        val address2 = generatedView.findViewById<TextView>(R.id.textViewCityAddress2)

        name.text = data[p0].GA_KNAME
        year.text = data[p0].GA_INS_DATE
        address1.text = data[p0].GA_ADDR1
        address2.text = data[p0].GA_ADDR2

        return generatedView
    }
}