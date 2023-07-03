package com.enesselcuk.moviesui.source.model.response

import com.google.gson.annotations.SerializedName

data class ActorTvResponse(
    @SerializedName("cast")
    val cast: List<CastTv>?=null,
    @SerializedName("crew")
    val crew: List<Any>?=null,
    @SerializedName("id")
    val id: Int
)

data class CastTv(
    val adult: Boolean?=null,
    val backdrop_path: String?=null,
    val character: String?=null,
    val credit_id: String?=null,
    val episode_count: Int?=null,
    val first_air_date: String?=null,
    val genre_ids: List<Int>?=null,
    val id: Int?=null,
    val name: String?=null,
    val origin_country: List<String>?=null,
    val original_language: String?=null,
    val original_name: String?=null,
    val overview: String?=null,
    val popularity: Double?=null,
    val poster_path: String?=null,
    val vote_average: Double?=null,
    val vote_count: Int?=null,
)