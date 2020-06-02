package com.example.covid19.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import com.example.covid19.R
import kotlinx.android.synthetic.main.activity_web_view.*

class WebViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        if(intent != null){
            webview.loadUrl("${intent.getStringExtra("url")}")
            webview.webViewClient = WebViewClient()
        }
    }
}
