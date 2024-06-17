package com.enesselcuk.moviesui.util


import com.enesselcuk.moviesui.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response


class NetworkConnectionInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        try {
            request = request.newBuilder()
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", BuildConfig.BEAR_TOKEN)
                .build()

        } catch (ex: Exception) {
            val exception = ex
        }
        return chain.proceed(request)
    }
}
