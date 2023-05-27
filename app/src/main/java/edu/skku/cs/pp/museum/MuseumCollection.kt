package edu.skku.cs.pp.museum

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import edu.skku.cs.pp.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.*
import okio.IOException
import java.net.URLEncoder

class MuseumCollection : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_museum_collection)

        /**
         * 요청인자
         * KEY, TYPE, SERVICE, START_INDEX, END_INDEX
         * manage_no_year, prdct_nm_korean, thumb_image, writr_nm
         * 수집연도, 작품명(국문), 썸네일이미지, 작가명
         */
        val key = "6a64734f4b6e61763738664645534e"
        val type = "json"
        val service = "SemaPsgudInfoKorInfo"
        val startIndex = "1"
        val endIndex = "500"

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
                    val dataParse = Gson().fromJson(data, Art::class.java).SemaPsgudInfoKorInfo.row
                    val artJson = Gson().toJson(dataParse)

                    val typeToken = object : TypeToken<ArrayList<Art.Information.ArtInfo>>() {}.type
                    val arts = Gson().fromJson<ArrayList<Art.Information.ArtInfo>>(artJson, typeToken)

                    CoroutineScope(Dispatchers.Main).launch {
                        val collectionAdapter = CollectionAdapter(this@MuseumCollection, arts)
                        val listViewCollection = findViewById<ListView>(R.id.listViewCity)
                        listViewCollection.adapter = collectionAdapter
                    }

                    /**
                     * 검색 기능
                     */
                    val btnSearch = findViewById<Button>(R.id.btnSearch)
                    btnSearch.setOnClickListener {
                        val searchText:String = findViewById<EditText>(R.id.editTextSearch).text.toString()
                        val searchArts = ArrayList<Art.Information.ArtInfo>()

                        for (art in arts) {
                            if (art.prdct_nm_korean.contains(searchText) || art.writr_nm.contains(searchText)) {
                                searchArts.add(art)
                            }
                        }

                        CoroutineScope(Dispatchers.Main).launch {
                            val collectionAdapter = CollectionAdapter(this@MuseumCollection, searchArts)
                            val listViewCollection = findViewById<ListView>(R.id.listViewCity)
                            listViewCollection.adapter = collectionAdapter
                        }
                    }
                }
            }
        })
    }
}