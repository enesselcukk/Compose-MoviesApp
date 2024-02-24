package com.enesselcuk.moviesui.util

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor

object ChuckerHelper {

    private var chuckerInterceptor: ChuckerInterceptor? = null
    fun create(context: Context) {
        chuckerInterceptor = ChuckerInterceptor(context)
    }

    fun getInterceptor(): ChuckerInterceptor? {
        return chuckerInterceptor
    }
}