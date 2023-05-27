package edu.skku.cs.pp.museum

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import edu.skku.cs.pp.R
import edu.skku.cs.pp.constant.Names
import edu.skku.cs.pp.constant.Names.Companion.SEOUL_MUSEUM

class MuseumMain : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_museum_main)

        /**
         * 구글 맵
         * 서울시립미술관 지도
         */
        val mapFragment: SupportMapFragment = supportFragmentManager.findFragmentById(R.id.mapView) as SupportMapFragment
        mapFragment.getMapAsync(this)

        /**
         * 소장품 정보 이동 버튼
         * 방문자 리뷰 이동 버튼
         */
        val btnCollection = findViewById<Button>(R.id.btnCollection)
        val btnReview = findViewById<Button>(R.id.btnReview)

        btnCollection.setOnClickListener {
            startActivity(Intent(this, MuseumCollection::class.java))
        }

        btnReview.setOnClickListener {
            this.startActivity(Intent(this, MuseumReview::class.java))
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val marker = LatLng(37.5640625, 126.9738125)
        mMap.addMarker(MarkerOptions().position(marker).title(SEOUL_MUSEUM))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marker, 15F))
    }
}