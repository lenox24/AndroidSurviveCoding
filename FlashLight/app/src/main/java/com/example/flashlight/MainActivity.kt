package com.example.flashlight

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val torch = Torch(this)

        switch_flash.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked) {
                torch.flashOn()
            } else {
                torch.flashOff()
            }
        }
    }
}