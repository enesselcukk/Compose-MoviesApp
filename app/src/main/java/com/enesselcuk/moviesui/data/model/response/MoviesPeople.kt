package com.enesselcuk.moviesui.data.model.response

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

data class MoviesPeople(
    @field:SerializedName("page") val page: Int,
    @field:SerializedName("results") val results: List<Resultx>,
    @field:SerializedName("total_pages") val totalPages: Int,
    @field:SerializedName("total_results") val totalResults: Int
)

data class KnownFor(
    @field:SerializedName("adult")val adult: Boolean,
    @field:SerializedName("backdrop_path") val backdrop_path: String,
    @field:SerializedName("first_air_date") val first_air_date: String,
    @field:SerializedName("genre_ids") val genre_ids: List<Int>,
    @field:SerializedName("id") val id: Int,
    @field:SerializedName("media_type") val media_type: String,
    @field:SerializedName("name") val name: String,
    @field:SerializedName("origin_country") val origin_country: List<String>,
    @field:SerializedName("original_language") val original_language: String,
    @field:SerializedName("original_name") val original_name: String,
    @field:SerializedName("original_title") val original_title: String,
    @field:SerializedName("overview") val overview: String,
    @field:SerializedName("popularity") val popularity: Double,
    @field:SerializedName("poster_path") val poster_path: String,
    @field:SerializedName("release_date") val release_date: String,
    @field:SerializedName("title") val title: String,
    @field:SerializedName("video") val video: Boolean,
    @field:SerializedName("vote_average") val vote_average: Double,
    @field:SerializedName("vote_count") val vote_count: Int
)

data class Resultx(
    @field:SerializedName("adult") val adult: Boolean,
    @field:SerializedName("gender") val gender: Int,
    @field:SerializedName("id") val id: Int,
    @field:SerializedName("known_for") val known_for: List<KnownFor>,
    @field:SerializedName("known_for_department") val known_for_department: String,
    @field:SerializedName("name") val name: String,
    @field:SerializedName("original_name") val original_name: String,
    @field:SerializedName("popularity") val popularity: Double,
    @field:SerializedName("profile_path") val profile_path: String
)