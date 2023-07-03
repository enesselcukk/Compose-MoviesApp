package com.enesselcuk.moviesui.util


import java.text.SimpleDateFormat
import java.util.*

fun String.dateCurrent(): String {
    val dateFormatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val date = dateFormatter.parse(this)
    return SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(date)
}

