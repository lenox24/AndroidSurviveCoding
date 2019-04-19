package com.example.bmicalculator

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val intent: Intent = intent
        val weight: Double = intent.getStringExtra("weight").toDouble()
        val height: Double = intent.getStringExtra("height").toDouble()

        val bmi: Double = weight / Math.pow(height/100.0, 2.0)
        Log.d("BMI : ","bmi : ${bmi.toString()}")

        when {
            bmi >= 35 -> tex_view_result.text = "고도 비만"
            bmi >= 30 -> tex_view_result.text = "2단계 비만"
            bmi >= 25 -> tex_view_result.text = "1단계 비만"
            bmi >= 23 -> tex_view_result.text = "과체중"
            bmi >= 18.5 -> tex_view_result.text = "정상"
            else -> tex_view_result.text = "저체중"
        }

        when {
            bmi >= 23 -> image_status_result.setImageResource(R.drawable.ic_sentiment_very_dissatisfied_black_24dp)
            bmi >= 18.5 -> image_status_result.setImageResource(R.drawable.ic_sentiment_satisfied_black_24dp)
            else -> image_status_result.setImageResource(R.drawable.ic_sentiment_dissatisfied_black_24dp)
        }
    }
}
