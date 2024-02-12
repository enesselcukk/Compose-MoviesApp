package com.enesselcuk.moviesui.data.repos.reposLocal

import com.enesselcuk.moviesui.domain.repository.ReposLocal
import com.enesselcuk.moviesui.data.local.MoviesDatabase
import com.enesselcuk.moviesui.data.model.response.DetailResponse
import com.enesselcuk.moviesui.data.model.response.TvDetailResponse
import com.enesselcuk.moviesui.util.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RepositoryLocalImpl @Inject constructor(val dataStore: MoviesDatabase) : ReposLocal {
    override suspend fun getLiked(): Flow<NetworkResult<List<DetailResponse>>> = flow {
        emit(NetworkResult.Loading())
        try {
            val getLiked = dataStore.moviesDao().allMovies()
            emit(NetworkResult.Success(getLiked))
        } catch (ex: Exception) {
            emit(NetworkResult.Error(ex.message.toString()))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun insertLiked(detailResponse: DetailResponse): Flow<NetworkResult<Any>> =
        flow {
            emit(NetworkResult.Loading())
            try {
                val insert = dataStore.moviesDao().insertMovies(detailResponse)
                emit(NetworkResult.Success(insert))
            } catch (ex: Exception) {
                emit(NetworkResult.Error(ex.message.toString()))
            }
        }.flowOn(Dispatchers.IO)

    override suspend fun delete(id: Int): Int {
        return dataStore.moviesDao().deleteID(id)
    }

    override suspend fun getTvLiked(): Flow<NetworkResult<List<TvDetailResponse>>> = flow {
        emit(NetworkResult.Loading())
        try {
            val getLiked = dataStore.tvDao().allTv()
            emit(NetworkResult.Success(getLiked))
        } catch (ex: Exception) {
            emit(NetworkResult.Error(ex.message.toString()))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun insertTvLiked(tvResponse: TvDetailResponse): Flow<NetworkResult<Any>> = flow {
            emit(NetworkResult.Loading())
            try {
                val insert = dataStore.tvDao().insertTv(tvResponse)
                emit(NetworkResult.Success(insert))
            } catch (ex: Exception) {
                emit(NetworkResult.Error(ex.message.toString()))
            }
        }.flowOn(Dispatchers.IO)


    override suspend fun deleteTv(id: Int): Int {
        return dataStore.tvDao().deleteID(id)
    }

}