package com.example.mywebbrowser

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.inputmethod.EditorInfo
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        view_web_main.apply {
            settings.javaScriptEnabled = true
            webViewClient = WebViewClient()
        }

        view_web_main.loadUrl("https://www.google.com")

        edt_url_main.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                view_web_main.loadUrl(edt_url_main.text.toString())
                true
            } else {
                false
            }
        }
    }

    override fun onBackPressed() {
        if (view_web_main.canGoBack()) {
            view_web_main.goBack()
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_google, R.id.menu_home -> {
                view_web_main.loadUrl("http://www.google.com")
                return true
            }

            R.id.action_naver -> {
                view_web_main.loadUrl("http://www.naver.com")
                return true
            }

            R.id.action_daum -> {
                view_web_main.loadUrl("http://daum.net")
                return true
            }

            R.id.action_call -> {
                val intent = Intent(Intent.ACTION_DIAL)
                intent.data = Uri.parse("tel:031-123-4567")
                if (intent.resolveActivity(packageManager) != null) {
                    startActivity(intent)
                }
                return true
            }

            R.id.action_send_text -> {

            }

            R.id.action_email -> {

            }
        }
    }
}
