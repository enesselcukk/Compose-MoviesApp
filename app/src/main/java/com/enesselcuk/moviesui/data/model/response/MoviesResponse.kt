package com.enesselcuk.moviesui.data.model.response

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity("MoviesEntity")
data class MoviesResponse(
    @PrimaryKey(autoGenerate = true) @SerializedName("id") val id: Int? = null,
    @field:SerializedName("page") val page: Int? = null,
    @field:SerializedName("results") val results: List<Result>? = null,
    @field:SerializedName("total_pages") val total_pages: Int? = null,
    @field:SerializedName("total_results") val total_results: Int? = null,
)

data class Result(
    @field:SerializedName("id") val id: Int? = null,
    @field:SerializedName("adult") val adult: Boolean? = null,
    @field:SerializedName("backdrop_path") val backdrop_path: String? = null,
    @field:SerializedName("genre_ids") val genre_ids: List<Int>? = null,
    @field:SerializedName("original_language") val original_language: String? = null,
    @field:SerializedName("original_title") val original_title: String? = null,
    @field:SerializedName("overview") val overview: String? = null,
    @field:SerializedName("popularity") val popularity: Double? = null,
    @field:SerializedName("poster_path") val poster_path: String? = null,
    @field:SerializedName("release_date") val release_date: String? = null,
    @field:SerializedName("title") val title: String? = null,
    @field:SerializedName("video") val video: Boolean? = null,
    @field:SerializedName("vote_average") val vote_average: Double? = null,
    @field:SerializedName("vote_count") val vote_count: Int? = null,
)