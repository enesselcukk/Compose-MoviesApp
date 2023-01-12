package com.enesselcuk.moviesui.source.data

import com.enesselcuk.moviesui.R

data class MoviesRecommended(
    val moviesName: Int,
    val moviesImage: Int,
    val moviesHour: Int,
    val moviesPoint: Int
)

val moviesRecommended = listOf(
    MoviesRecommended(
        R.string.film1,
        R.drawable.moonfall,
        R.string.time,
        R.string.point,
    ),
    MoviesRecommended(
        R.string.film2,
        R.drawable.kuzeyli,
        R.string.time,
        R.string.point,
    ),
    MoviesRecommended(
        R.string.film3,
        R.drawable.troll,
        R.string.time,
        R.string.point,
    ),
    MoviesRecommended(
        R.string.film4,
        R.drawable.iyilik_ve_kotuluk,
        R.string.time,
        R.string.point,
    ),
    MoviesRecommended(
        R.string.film5,
        R.drawable.zamanda_tutsak,
        R.string.time,
        R.string.point,
    ),
    MoviesRecommended(
        R.string.film6,
        R.drawable.jaula,
        R.string.time,
        R.string.point,
    ),
    MoviesRecommended(
        R.string.film7,
        R.drawable.morbios,
        R.string.time,
        R.string.point,
    )

)
