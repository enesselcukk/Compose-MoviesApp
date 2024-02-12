package com.enesselcuk.moviesui.data.model.response

import androidx.room.Entity
import com.google.gson.annotations.SerializedName


data class TvRecommendationsResponse(
    @field:SerializedName("page") val page: Int? = null,
    @field:SerializedName("results") val results: List<TvResult>? = null,
    @field:SerializedName("total_pages") val total_pages: Int? = null,
    @field:SerializedName("total_results") val total_results: Int? = null,
)

data class TvResult(
    @field:SerializedName("adult") val adult: Boolean? = null,
    @field:SerializedName("backdrop_path") val backdrop_path: String? = null,
    @field:SerializedName("first_air_date") val first_air_date: String? = null,
    @field:SerializedName("genre_ids") val genre_ids: List<Int>? = null,
    @field:SerializedName("id") val id: Int? = null,
    @field:SerializedName("media_type") val media_type: String? = null,
    @field:SerializedName("name") val name: String? = null,
    @field:SerializedName("origin_country") val origin_country: List<String>? = null,
    @field:SerializedName("original_language") val original_language: String? = null,
    @field:SerializedName("original_name") val original_name: String? = null,
    @field:SerializedName("overview") val overview: String? = null,
    @field:SerializedName("popularity") val popularity: Double? = null,
    @field:SerializedName("poster_path") val poster_path: String? = null,
    @field:SerializedName("vote_average") val vote_average: Double? = null,
    @field:SerializedName("vote_count") val vote_count: Int? = null,
)