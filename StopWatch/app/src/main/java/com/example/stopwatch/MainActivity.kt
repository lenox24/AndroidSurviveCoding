package com.example.stopwatch

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView.setOnClickListener {
            val str: Int = editText.text.toString().toInt()

            Toast.makeText(this, str, Toast.LENGTH_SHORT).show()
        }
    }
}
