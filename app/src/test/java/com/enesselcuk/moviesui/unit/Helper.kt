package com.enesselcuk.moviesui.unit

import android.util.Log
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source

fun MockWebServer.enqueueResponse(fileName: String, headers: Map<String, String> = emptyMap()) {
    val inputStream = javaClass.classLoader!!.getResourceAsStream(fileName)
    if (inputStream == null) {
        Log.i("dosya","dosya bulunamadı")
    }else {
        val source = inputStream.source().buffer()
        val mockResponse = MockResponse()
        for ((key, value) in headers) {
            mockResponse.addHeader(key, value)
        }
        this.enqueue(
            mockResponse.setBody(source.readString(Charsets.UTF_8))
        )
    }

}