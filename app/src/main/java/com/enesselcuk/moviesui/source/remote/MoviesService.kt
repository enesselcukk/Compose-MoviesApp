package com.enesselcuk.moviesui.source.remote

import com.enesselcuk.moviesui.source.model.MoviesResponse
import com.enesselcuk.moviesui.util.Constant.API_KEY
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesService {
    @GET("3/movie/{title}")
    suspend fun getMovies(
        @Path("movies") movies: String? = null,
        @Query("api_key") api_key: String = API_KEY,
        @Query("language") language: String? = null,
        @Query("page") page: Int? = null,
    ): MoviesResponse
}