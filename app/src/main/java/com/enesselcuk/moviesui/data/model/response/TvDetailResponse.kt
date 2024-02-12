package com.enesselcuk.moviesui.data.model.response

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity("TvDetailEntity")
data class TvDetailResponse(
    @PrimaryKey @field:SerializedName("id") val id: Int? = null,

    @field:SerializedName("adult") val adult: Boolean? = null,
    @field:SerializedName("backdrop_path") val backdrop_path: String? = null,
    @field:SerializedName("createdBy") val created_by: List<Any>? = emptyList(),
    @field:SerializedName("episode_run_time") val episode_run_time: List<Int>? = null,
    @field:SerializedName("first_air_date") val first_air_date: String? = null,
    @field:SerializedName("genres") val genres: List<TvGenre>? = null,
    @field:SerializedName("homepage") val homepage: String? = null,
    @field:SerializedName("in_production") val in_production: Boolean? = null,
    @field:SerializedName("languages") val languages: List<String>? = null,
    @field:SerializedName("last_air_date") val last_air_date: String? = null,
    @field:SerializedName("last_episode_to_air") val last_episode_to_air: LastEpisodeToAir? = null,
    @field:SerializedName("name") val name: String? = null,
    @field:SerializedName("networks") val networks: List<Network>? = null,
    @field:SerializedName("next_episode_to_air") val next_episode_to_air: Any? = null,
    @field:SerializedName("number_of_episodes") val number_of_episodes: Int? = null,
    @field:SerializedName("number_of_seasons") val number_of_seasons: Int? = null,
    @field:SerializedName("origin_country") val origin_country: List<String>? = null,
    @field:SerializedName("original_language") val original_language: String? = null,
    @field:SerializedName("original_name") val original_name: String? = null,
    @field:SerializedName("overview") val overview: String? = null,
    @field:SerializedName("popularity") val popularity: Double? = null,
    @field:SerializedName("poster_path") val poster_path: String? = null,
    @field:SerializedName("production_companies") val production_companies: List<TvProductionCompany>? = null,
    @field:SerializedName("production_countries") val production_countries: List<TvProductionCountry>? = null,
    @field:SerializedName("seasons") val seasons: List<Season>? = null,
    @field:SerializedName("spoken_languages") val spoken_languages: List<TvSpokenLanguage>? = null,
    @field:SerializedName("status") val status: String? = null,
    @field:SerializedName("tagline") val tagline: String? = null,
    @field:SerializedName("type") val type: String? = null,
    @field:SerializedName("vote_average") val vote_average: Double? = null,
    @field:SerializedName("vote_count") val vote_count: Int? = null,
)

data class TvGenre(
    @field:SerializedName("id") val id: Int? = null,
    @field:SerializedName("name") val name: String? = null,
)

data class LastEpisodeToAir(
    @field:SerializedName("air_date") val air_date: String? = null,
    @field:SerializedName("episode_number") val episode_number: Int? = null,
    @field:SerializedName("id") val id: Int? = null,
    @field:SerializedName("name") val name: String? = null,
    @field:SerializedName("overview") val overview: String? = null,
    @field:SerializedName("production_code") val production_code: String? = null,
    @field:SerializedName("runtime") val runtime: Any? = null,
    @field:SerializedName("season_number") val season_number: Int? = null,
    @field:SerializedName("show_id") val show_id: Int? = null,
    @field:SerializedName("still_path") val still_path: Any? = null,
    @field:SerializedName("vote_average") val vote_average: Double? = null,
    @field:SerializedName("vote_count") val vote_count: Int? = null,
)

data class Network(
    @field:SerializedName("id") val id: Int? = null,
    @field:SerializedName("logo_path") val logo_path: String? = null,
    @field:SerializedName("name") val name: String? = null,
    @field:SerializedName("origin_country") val origin_country: String? = null,
)

data class TvProductionCompany(
    @field:SerializedName("id") val id: Int? = null,
    @field:SerializedName("logo_path") val logo_path: Any? = null,
    @field:SerializedName("name") val name: String? = null,
    @field:SerializedName("origin_country") val origin_country: String? = null,
)

data class TvProductionCountry(
    @field:SerializedName("iso_3166_1") val iso_3166_1: String? = null,
    @field:SerializedName("name") val name: String? = null,
)

data class Season(
    @field:SerializedName("air_date") val air_date: String? = null,
    @field:SerializedName("episode_count") val episode_count: Int? = null,
    @field:SerializedName("id") val id: Int? = null,
    @field:SerializedName("name") val name: String? = null,
    @field:SerializedName("overview") val overview: String? = null,
    @field:SerializedName("poster_path") val poster_path: Any? = null,
    @field:SerializedName("season_number") val season_number: Int? = null,
)

data class TvSpokenLanguage(
    @field:SerializedName("english_name") val english_name: String? = null,
    @field:SerializedName("iso_639_1") val iso_639_1: String? = null,
    @field:SerializedName("name") val name: String? = null,
)