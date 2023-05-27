package edu.skku.cs.pp.museum

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ListView
import edu.skku.cs.pp.R
import edu.skku.cs.pp.constant.Names.Companion.REVIEW1_DATE
import edu.skku.cs.pp.constant.Names.Companion.REVIEW1_NAME
import edu.skku.cs.pp.constant.Names.Companion.REVIEW1_TEXT
import edu.skku.cs.pp.constant.Names.Companion.REVIEW2_DATE
import edu.skku.cs.pp.constant.Names.Companion.REVIEW2_NAME
import edu.skku.cs.pp.constant.Names.Companion.REVIEW2_TEXT
import edu.skku.cs.pp.constant.Names.Companion.REVIEW3_DATE
import edu.skku.cs.pp.constant.Names.Companion.REVIEW3_NAME
import edu.skku.cs.pp.constant.Names.Companion.REVIEW3_TEXT

class MuseumReview : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_museum_review)

        val reviews = ArrayList<Review>()

        /**
         * 만일 리뷰를 작성 하고 돌아올 시 intent 에서 객체를 받아 온다
         */
        getIntent(reviews)

        /**
         * 예시 리뷰들을 추가한다
         */
        addExampleReviews(reviews)

        /**
         * 리뷰 리스트 표시
         */
        val reviewAdapter = MuseumReviewAdapter(this@MuseumReview, reviews)
        val listViewReview = findViewById<ListView>(R.id.listViewReview)
        listViewReview.adapter = reviewAdapter

        /**
         * 리뷰 글쓰기 버튼
         */
        val btnWriteReview = findViewById<Button>(R.id.btnWriteReview)
        btnWriteReview.setOnClickListener {
            this.startActivity(Intent(this, WriteReview::class.java))
            finish()
        }
    }

    private fun getIntent(reviews: ArrayList<Review>) {
        val intentName = intent.getStringExtra(WriteReview.EXT_NAME)
        val intentDate = intent.getStringExtra(WriteReview.EXT_DATE)
        val intentText = intent.getStringExtra(WriteReview.EXT_TEXT)
        if (ImageViewData().getCount() > 0) {
            val intentViewList = ArrayList<View>()

            for (uri in ImageViewData().getData2()) {
                var tempView = ImageView(this)
                tempView.setImageURI(uri)
                tempView.scaleType = ImageView.ScaleType.FIT_END
                tempView.adjustViewBounds = true
                intentViewList.add(tempView)
            }

            ImageViewData().clearData()

            reviews.add(
                Review(
                    intentName.toString(),
                    intentDate.toString(),
                    intentText.toString(),
                    intentViewList
                )
            )
        }
    }


    private fun addExampleReviews(reviews: ArrayList<Review>) {

        // Review1
        val reviewImages1 = ArrayList<View>()
        var reviewImage1 = ImageView(this)
        var reviewImage2 = ImageView(this)
        reviewImage1.setBackgroundResource(R.drawable.img1)
        reviewImage2.setBackgroundResource(R.drawable.img2)
        reviewImage1.scaleType = ImageView.ScaleType.FIT_END
        reviewImage2.scaleType = ImageView.ScaleType.FIT_END
        reviewImage1.adjustViewBounds = true
        reviewImage2.adjustViewBounds = true
        reviewImages1.add(reviewImage1)
        reviewImages1.add(reviewImage2)

        reviews.add(Review(REVIEW1_NAME, REVIEW1_DATE, REVIEW1_TEXT, reviewImages1))

        // Review2
        val reviewImages2 = ArrayList<View>()
        var reviewImage2_1 = ImageView(this)
        reviewImage2_1.setBackgroundResource(R.drawable.img3)
        reviewImage2_1.scaleType = ImageView.ScaleType.FIT_END
        reviewImage2_1.adjustViewBounds = true
        reviewImages2.add(reviewImage2_1)

        reviews.add(Review(REVIEW2_NAME, REVIEW2_DATE, REVIEW2_TEXT, reviewImages2))

        // Review3
        val reviewImages3 = ArrayList<View>()
        var reviewImage3_1 = ImageView(this)
        var reviewImage3_2 = ImageView(this)
        var reviewImage3_3 = ImageView(this)
        reviewImage3_1.setBackgroundResource(R.drawable.img4)
        reviewImage3_2.setBackgroundResource(R.drawable.img5)
        reviewImage3_3.setBackgroundResource(R.drawable.img6)
        reviewImage3_1.scaleType = ImageView.ScaleType.FIT_XY
        reviewImage3_2.scaleType = ImageView.ScaleType.FIT_XY
        reviewImage3_3.scaleType = ImageView.ScaleType.FIT_XY
        reviewImage3_1.adjustViewBounds = true
        reviewImage3_2.adjustViewBounds = true
        reviewImage3_3.adjustViewBounds = true
        reviewImages3.add(reviewImage3_1)
        reviewImages3.add(reviewImage3_2)
        reviewImages3.add(reviewImage3_3)

        reviews.add(Review(REVIEW3_NAME, REVIEW3_DATE, REVIEW3_TEXT, reviewImages3))
    }
}