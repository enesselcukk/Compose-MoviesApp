package com.enesselcuk.moviesui.repos

import com.enesselcuk.moviesui.source.model.MoviesResponse
import com.enesselcuk.moviesui.util.NetworkResult
import kotlinx.coroutines.flow.Flow

interface Repos {
    suspend fun getMovies(
        movies: String?,
        language: String?,
        page: Int
    ): Flow<NetworkResult<MoviesResponse>>
}