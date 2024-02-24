package com.enesselcuk.moviesui.screensauth.userScreen.signIn

import android.webkit.WebView
import android.webkit.WebViewClient

open class CustomWebViewClient(private val completed:(Boolean) -> Unit) : WebViewClient(){
    @Deprecated("Deprecated in Java")
    override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
        if(url.isNullOrEmpty().not() && url.toString().endsWith("allow")){
            completed.invoke(false)
            return true
        }
        return false
    }
}
