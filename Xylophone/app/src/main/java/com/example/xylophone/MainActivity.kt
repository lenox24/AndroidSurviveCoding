package com.example.xylophone

import android.content.pm.ActivityInfo
import android.media.AudioManager
import android.media.MediaPlayer
import android.media.SoundPool
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.versionedparcelable.ParcelImpl
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val soundPool = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        SoundPool.Builder().setMaxStreams(8).build()
    } else {
        SoundPool(8, AudioManager.STREAM_MUSIC, 0)
    }

    private val sounds = listOf(
        Pair(R.id.btn_do1_main, R.raw.do1),
        Pair(R.id.btn_re_main, R.raw.re),
        Pair(R.id.btn_mi_main, R.raw.mi),
        Pair(R.id.btn_fa_main, R.raw.fa),
        Pair(R.id.btn_sol_main, R.raw.sol),
        Pair(R.id.btn_la_main, R.raw.la),
        Pair(R.id.btn_si_main, R.raw.si),
        Pair(R.id.btn_do2_main, R.raw.do2)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sounds.forEach { tune(it) }
    }

    private fun tune(pitch: Pair<Int, Int>) {
        val soundId = soundPool.load(this, pitch.second, 1)
        findViewById<TextView>(pitch.first).setOnClickListener {
            soundPool.play(soundId, 1.0f, 1.0f, 0, 0, 1.0f)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        soundPool.release()
    }
}
