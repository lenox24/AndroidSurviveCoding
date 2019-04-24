package com.example.stopwatch

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.concurrent.timer

class MainActivity : AppCompatActivity() {

    private var time = 0
    private var timerTask: Timer? = null
    private var isRunning = false
    private var lap = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fab_start_main.setOnClickListener {
            isRunning = !isRunning

            if (isRunning) {
                start()
            } else {
                pause()
            }
        }

        btn_lab_main.setOnClickListener {
            if (time != 0)
                recordLapTime()
        }

        fab_reset_main.setOnClickListener {
            reset()
        }
    }

    private fun start() {
        fab_start_main.setImageResource(R.drawable.ic_pause_black_24dp)

        timerTask = timer(period = 10) {
            time++
            val sec = time / 100
            val milli = time % 100

            runOnUiThread {
                tex_sec_main.text = "$sec"
                tex_msec_main.text = "$milli"
            }
        }
    }

    private fun pause() {
        fab_start_main.setImageResource(R.drawable.ic_play_arrow_black_24dp)
        timerTask?.cancel()
    }

    @SuppressLint("SetTextI18n")
    private fun recordLapTime() {
        val lapTime = this.time
        val textView = TextView(this)
        textView.text = "$lap LAP : ${lapTime / 100}.${lapTime % 100}"

        scroll_lap_main.addView(textView, 0)
        lap++
    }

    @SuppressLint("SetTextI18n")
    private fun reset() {
        timerTask?.cancel()

        time = 0
        isRunning = false
        fab_start_main.setImageResource(R.drawable.ic_play_arrow_black_24dp)
        tex_sec_main.text = "0"
        tex_msec_main.text = "00"

        scroll_lap_main.removeAllViews()
        lap = 1
    }
}
