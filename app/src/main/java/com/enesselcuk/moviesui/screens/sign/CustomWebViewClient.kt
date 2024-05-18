package com.enesselcuk.moviesui.screens.sign

import android.webkit.WebView
import android.webkit.WebViewClient

open class CustomWebViewClient(private val completed:(Boolean) -> Unit) : WebViewClient(){
    @Deprecated("Deprecated in Java")
    override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
         if(url.isNullOrEmpty().not() && url.toString().endsWith("allow")){
            completed(true)
           return true
        }else if(url.isNullOrEmpty().not() && url.toString().endsWith("deny")) {
            completed(false)
        }
        return false

    }
}
