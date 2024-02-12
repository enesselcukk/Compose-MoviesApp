package com.enesselcuk.moviesui.data.model.response

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity("detail_entity")
data class DetailResponse(
    @PrimaryKey @field:SerializedName("id") val  id: Int? = null,

    @field:SerializedName("adult") val  adult: Boolean? = null,
    @field:SerializedName("backdrop_path") val  backdrop_path: String? = null,
    @field:SerializedName("belongs_to_collection") val  belongs_to_collection: Any? = null,
    @field:SerializedName("budget") val  budget: Int? = null,
    @field:SerializedName("genres") val  genres: List<Genre>? = null,
    @field:SerializedName("homepage") val  homepage: String? = null,
    @field:SerializedName("imdb_id") val  imdb_id: String? = null,
    @field:SerializedName("original_language") val  original_language: String? = null,
    @field:SerializedName("original_title") val  original_title: String? = null,
    @field:SerializedName("overview") val  overview: String? = null,
    @field:SerializedName("popularity") val  popularity: Double? = null,
    @field:SerializedName("poster_path") val  poster_path: String? = null,
    @field:SerializedName("production_companies") val  production_companies: List<ProductionCompany>? = null,
    @field:SerializedName("production_countries") val  production_countries: List<ProductionCountry>? = null,
    @field:SerializedName("release_date") val  release_date: String? = null,
    @field:SerializedName("revenue") val  revenue: Int? = null,
    @field:SerializedName("runtime") val  runtime: Int? = null,
    @field:SerializedName("spoken_languages") val  spoken_languages: List<SpokenLanguage>? = null,
    @field:SerializedName("status") val  status: String? = null,
    @field:SerializedName("tagline") val  tagline: String? = null,
    @field:SerializedName("title") val  title: String? = null,
    @field:SerializedName("video") val  video: Boolean? = null,
    @field:SerializedName("vote_average") val  vote_average: Double? = null,
    @field:SerializedName("vote_count") val  vote_count: Int? = null,
)

data class Genre(
     @field:SerializedName("id") val  id: Int? = null,
     @field:SerializedName("name") val  name: String? = null,
)

data class ProductionCompany(
     @field:SerializedName("id") val  id: Int? = null,
     @field:SerializedName("logo_path") val  logo_path: String? = null,
     @field:SerializedName("name") val  name: String? = null,
     @field:SerializedName("origin_country") val  origin_country: String? = null,
)

data class ProductionCountry(
     @field:SerializedName("iso_3166_1") val  iso_3166_1: String? = null,
     @field:SerializedName("name") val  name: String? = null,
)

data class SpokenLanguage(
     @field:SerializedName("english_name") val  english_name: String? = null,
     @field:SerializedName("iso_639_1") val  iso_639_1: String? = null,
     @field:SerializedName("name") val  name: String? = null,
)