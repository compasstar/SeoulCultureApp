package edu.skku.cs.pp.museum

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.LinearLayout
import android.widget.TextView
import edu.skku.cs.pp.R

class MuseumReviewAdapter(val context: Context, val data:ArrayList<Review>): BaseAdapter() {

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
        val generatedView = inflater.inflate(R.layout.review, null)

        val name = generatedView.findViewById<TextView>(R.id.textViewReviewName)
        val date = generatedView.findViewById<TextView>(R.id.textViewReviewDate)
        val reviewText = generatedView.findViewById<TextView>(R.id.textViewReviewText)
        val linearLayout = generatedView.findViewById<LinearLayout>(R.id.linearLayoutReview)

        name.text = data[p0].name
        date.text = data[p0].date
        reviewText.text = data[p0].text

        for (image in data[p0].images) {
            val parent = image.parent as? ViewGroup
            parent?.removeView(image)
            linearLayout.addView(image)
        }

        return generatedView
    }
}