package com.enesselcuk.moviesui.source.model

import com.google.gson.annotations.SerializedName


data class MoviesResponse(
    @field:SerializedName("page")  val page: Int,
    @field:SerializedName("results") val results: List<Result>,
    @field:SerializedName("total_pages") val totalPages: Int,
    @field:SerializedName("total_results") val totalResults: Int
)