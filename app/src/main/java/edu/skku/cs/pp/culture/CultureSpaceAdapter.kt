package edu.skku.cs.pp.culture

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import edu.skku.cs.pp.R

class CultureSpaceAdapter(val context: Context, val data:ArrayList<CultureSpace.CultureInformation.CultureInfo>): BaseAdapter() {
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
        val generatedView = inflater.inflate(R.layout.culture_space, null)

        val name = generatedView.findViewById<TextView>(R.id.textViewCultureName)
        val address = generatedView.findViewById<TextView>(R.id.textViewCultureAddress)
        val phone = generatedView.findViewById<TextView>(R.id.textViewCulturePhone)
        val thumbnail = generatedView.findViewById<ImageView>(R.id.imageViewCultureThumbnail)

        name.text = data[p0].FAC_NAME
        address.text = data[p0].ADDR
        phone.text = data[p0].PHNE

        Glide.with(context).load(data[p0].MAIN_IMG).into(thumbnail)

        return generatedView
    }
}