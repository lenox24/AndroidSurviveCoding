package com.example.bmicalculator

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadData()

        btn_result_main.setOnClickListener {
            val height: String = edt_height_main.text.toString()
            val weight: String = edt_weight_main.text.toString()

            saveData(height, weight)

            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra("weight", weight)
            intent.putExtra("height", height)
            startActivity(intent)
        }
    }

    @SuppressLint("CommitPrefEdits")
    private fun saveData(height: String, weight: String) {
        val pref: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val editor: SharedPreferences.Editor = pref.edit()
        editor.putFloat("KEY_HEIGHT", height.toFloat())
            .putFloat("KEY_WEIGHT", weight.toFloat())
            .apply()
    }

    private fun loadData() {
        val pref: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val heightLoad: Double = pref.getFloat("KEY_HEIGHT", 0.0F).toDouble()
        val weightLoad: Double = pref.getFloat("KEY_WEIGHT", 0.0F).toDouble()

        if (weightLoad != 0.0 && heightLoad != 0.0) {
            edt_weight_main.text = Editable.Factory.getInstance().newEditable(weightLoad.toString())
            edt_height_main.text = Editable.Factory.getInstance().newEditable(heightLoad.toString())
        }
    }
}
