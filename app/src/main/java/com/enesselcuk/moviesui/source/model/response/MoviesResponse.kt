package com.enesselcuk.moviesui.source.model.response

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity("MoviesEntity")
data class MoviesResponse(
    @PrimaryKey(autoGenerate = true) @SerializedName("id") val id: Int? = null,
    @field:SerializedName("page") val page: Int,
    @field:SerializedName("results") val results: List<Result>,
    @field:SerializedName("total_pages") val totalPages: Int,
    @field:SerializedName("total_results") val totalResults: Int
)

data class Result(
    @field:SerializedName("id") val id: Int,
    @field:SerializedName("adult") val adult: Boolean,
    @field:SerializedName("backdrop_path") val backdropPath: String,
    @field:SerializedName("genre_ids") val genre_ids: List<Int>,
    @field:SerializedName("original_language") val originalLanguage: String,
    @field:SerializedName("original_title") val originalTitle: String,
    @field:SerializedName("overview") val overview: String,
    @field:SerializedName("popularity") val popularity: Double,
    @field:SerializedName("poster_path") val posterPath: String,
    @field:SerializedName("release_date") val releaseDate: String,
    @field:SerializedName("title") val title: String,
    @field:SerializedName("video") val video: Boolean,
    @field:SerializedName("vote_average") val voteAverage: Double,
    @field:SerializedName("vote_count") val voteCount: Int
)