package edu.skku.cs.pp.gallery

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import edu.skku.cs.pp.R
import edu.skku.cs.pp.culture.CultureSpace
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.*
import okio.IOException
import java.net.URLEncoder

class GalleryMain : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery_main)


        /**
         * 요청인자
         * KEY, TYPE, SERVICE, START_INDEX, END_INDEX
         * GA_KNAME, GA_INST_DATE, GA_ADDR1, GA_ADDR2
         * 작품명, 설치연도, 작품주소, 상세주소
         */
        val key = "464e77636b6e617635344144686654"
        val type = "json"
        val service = "gongGongArtGallery"
        val startIndex = "1"
        val endIndex = "37"

        val urlBuilder = StringBuilder("http://openapi.seoul.go.kr:8088")
        urlBuilder.append("/" + URLEncoder.encode(key, "UTF-8"))
        urlBuilder.append("/" + URLEncoder.encode(type, "UTF-8"))
        urlBuilder.append("/" + URLEncoder.encode(service, "UTF-8"))
        urlBuilder.append("/" + URLEncoder.encode(startIndex, "UTF-8"))
        urlBuilder.append("/" + URLEncoder.encode(endIndex, "UTF-8"))

        val url = urlBuilder.toString()
        val client = OkHttpClient()
        val request = Request.Builder().url(url).build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful) throw IOException("Unexpected code $response")
                    val data = response.body!!.string()
                    val dataParse = Gson().fromJson(data, Gallery::class.java).gongGongArtGallery.row
                    val galleryJson = Gson().toJson(dataParse)

                    val typeToken = object : TypeToken<ArrayList<Gallery.GalleryInformation.GalleryInfo>>() {}.type
                    val galleries = Gson().fromJson<ArrayList<Gallery.GalleryInformation.GalleryInfo>>(galleryJson, typeToken)

                    CoroutineScope(Dispatchers.Main).launch {
                        val galleryAdapter = GalleryAdapter(this@GalleryMain, galleries)
                        val listViewGallery = findViewById<ListView>(R.id.listViewCity)
                        listViewGallery.adapter = galleryAdapter
                    }

                    /**
                     * 검색 기능
                     */
                    val btnGallerySearch = findViewById<Button>(R.id.btnCitySearch)
                    btnGallerySearch.setOnClickListener {
                        val searchText:String = findViewById<EditText>(R.id.editTextCitySearch).text.toString()
                        val searchGalleries = ArrayList<Gallery.GalleryInformation.GalleryInfo>()

                        for (gallery in galleries) {
                            if (gallery.GA_KNAME.contains(searchText)) {
                                searchGalleries.add(gallery)
                            }
                        }

                        CoroutineScope(Dispatchers.Main).launch {
                            val galleryAdapter = GalleryAdapter(this@GalleryMain, searchGalleries)
                            val listViewGallery = findViewById<ListView>(R.id.listViewCity)
                            listViewGallery.adapter = galleryAdapter
                        }
                    }
                }
            }
        })

    }
}