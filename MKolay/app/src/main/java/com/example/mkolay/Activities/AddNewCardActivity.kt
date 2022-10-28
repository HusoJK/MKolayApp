package com.example.mkolay.Activities

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.mkolay.R

class AddNewCardActivity : AppCompatActivity() {
    lateinit var webView:WebView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_card)
        webView = findViewById(R.id.webview)

        webViewSetup()
    }
    
    private fun webViewSetup(){
        webView.webViewClient = WebViewClient()

        webView.apply {
            loadUrl("https://api-test.moneypay.com.tr:8743/#/list?userToken=73786f6d6b6e4f636f6c6e726c757371")
            settings.javaScriptEnabled = true
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                settings.safeBrowsingEnabled = true
            }
        }
    }

    override fun onBackPressed() {
        if(webView.canGoBack()) webView.goBack() else super.onBackPressed()
    }
}