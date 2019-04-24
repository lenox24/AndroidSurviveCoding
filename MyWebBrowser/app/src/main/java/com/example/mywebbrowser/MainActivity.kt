package com.example.mywebbrowser

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
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
        if(view_web_main.canGoBack()) {
            view_web_main.goBack()
        } else {
            super.onBackPressed()
        }
    }
}
