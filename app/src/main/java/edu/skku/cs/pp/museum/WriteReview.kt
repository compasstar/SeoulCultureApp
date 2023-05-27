package edu.skku.cs.pp.museum

import android.app.Activity
import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import edu.skku.cs.pp.R
import java.util.Calendar

class WriteReview : AppCompatActivity() {

    companion object {
        val EXT_NAME = "name"
        val EXT_DATE = "date"
        val EXT_TEXT = "text"
    }

    private val REQUEST_CODE = 1
    private lateinit var linearLayoutAddImage: LinearLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write_review)

        linearLayoutAddImage = findViewById(R.id.linearLayoutImages)
        val name = findViewById<EditText>(R.id.editTextWriteName)
        val reviewText = findViewById<EditText>(R.id.editTextWriteReview)

        /**
         * 이미지 첨부
         */
        val btnAddImage = findViewById<Button>(R.id.btnAddImage)
        btnAddImage.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, REQUEST_CODE)
        }

        /**
         * 날짜 선택
         */
        val btnCalendar = findViewById<Button>(R.id.btnDate)
        btnCalendar.setOnClickListener {
            selectDate(btnCalendar)
        }

        /**
         * 완료시 intent Review 넘기기
         */
        val btnFinish = findViewById<Button>(R.id.btnFinish)
        btnFinish.setOnClickListener {
            completeReview(btnCalendar, name, reviewText)
        }
    }


    private fun completeReview(
        btnCalendar: Button,
        name: EditText,
        reviewText: EditText
    ) {
        // 날짜 선택을 안했을 경우 오늘로 날짜를 바꾼다
        initDate(btnCalendar)

        //start Activity: MuseumReview::class.java
        intentActivity(name, btnCalendar, reviewText)
    }

    private fun intentActivity(
        name: EditText,
        btnCalendar: Button,
        reviewText: EditText
    ) {
        val intent = Intent(this, MuseumReview::class.java).apply {
            putExtra(EXT_NAME, name.text.toString())
            putExtra(EXT_DATE, btnCalendar.text.toString())
            putExtra(EXT_TEXT, reviewText.text.toString())
        }
        startActivity(intent)
        finish()
    }

    private fun initDate(btnCalendar: Button) {
        if (btnCalendar.text.toString() == "날짜") {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
            btnCalendar.text = "$year-${month + 1}-$dayOfMonth"
        }
    }

    private fun selectDate(btnCalendar: Button) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            OnDateSetListener { _, selectedYear, selectedMonth, selectedDayOfMonth ->
                val selectedDate = "$selectedYear-${selectedMonth + 1}-$selectedDayOfMonth"
                btnCalendar.text = selectedDate
            },
            year, month, dayOfMonth
        )
        datePickerDialog.show()
    }

    private fun selectDate() {

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val imageUri = data?.data

            var addImage = ImageView(this)
            addImage.setImageURI(imageUri)

            addImage.scaleType = ImageView.ScaleType.FIT_END
            addImage.adjustViewBounds = true
            linearLayoutAddImage.addView(addImage)

            ImageViewData().addData(imageUri)
        }
    }
}