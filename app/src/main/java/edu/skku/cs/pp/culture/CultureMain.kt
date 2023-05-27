package edu.skku.cs.pp.culture

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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

class CultureMain : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_culture_main)


        /**
         * 요청인자
         * KEY, TYPE, SERVICE, START_INDEX, END_INDEX
         * FAC_NAME, ADDR, PHNE, MAIN_IMG
         * 문화시설명, 주소, 전화번호, 대표이미지
         */
        val key = "73427a72776e617635307952507656"
        val type = "json"
        val service = "culturalSpaceInfo"
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
                    val dataParse = Gson().fromJson(data, CultureSpace::class.java).culturalSpaceInfo.row
                    val cultureJson = Gson().toJson(dataParse)

                    val typeToken = object : TypeToken<ArrayList<CultureSpace.CultureInformation.CultureInfo>>() {}.type
                    val cultureSpaces = Gson().fromJson<ArrayList<CultureSpace.CultureInformation.CultureInfo>>(cultureJson, typeToken)

                    CoroutineScope(Dispatchers.Main).launch {
                        val cultureSpaceAdapter = CultureSpaceAdapter(this@CultureMain, cultureSpaces)
                        val listViewCulture = findViewById<ListView>(R.id.listViewCity)
                        listViewCulture.adapter = cultureSpaceAdapter
                    }


                    /**
                     * 검색 기능
                     */
                    val btnCultureSearch = findViewById<Button>(R.id.btnCultureSearch)
                    btnCultureSearch.setOnClickListener {
                        val searchText:String = findViewById<EditText>(R.id.editTextCultureSearch).text.toString()
                        val searchSpaces = ArrayList<CultureSpace.CultureInformation.CultureInfo>()

                        for (space in cultureSpaces) {
                            if (space.FAC_NAME.contains(searchText)) {
                                searchSpaces.add(space)
                            }
                        }

                        CoroutineScope(Dispatchers.Main).launch {
                            val cultureSpaceAdapter = CultureSpaceAdapter(this@CultureMain, searchSpaces)
                            val listViewCulture = findViewById<ListView>(R.id.listViewCity)
                            listViewCulture.adapter = cultureSpaceAdapter
                        }
                    }
                }
            }
        })


    }
}