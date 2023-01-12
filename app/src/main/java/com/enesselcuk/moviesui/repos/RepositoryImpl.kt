package com.enesselcuk.moviesui.repos

import com.enesselcuk.moviesui.source.model.MoviesResponse
import com.enesselcuk.moviesui.source.remote.MoviesService
import com.enesselcuk.moviesui.util.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val remoteDataSource: MoviesService
    // private val localDataSource :
) : Repos {
    override suspend fun getMovies(
        movies: String?,
        language: String?,
        page: Int
    ): Flow<NetworkResult<MoviesResponse>> = flow {
        emit(NetworkResult.Loading())
        val response = remoteDataSource.getMovies(movies = movies, language = language, page = page)
        emit(NetworkResult.Success(response))

    }.flowOn(Dispatchers.IO)
        .catch { emit(NetworkResult.Error(it.message)) }

}