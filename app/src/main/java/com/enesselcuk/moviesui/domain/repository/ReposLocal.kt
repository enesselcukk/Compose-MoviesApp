package com.enesselcuk.moviesui.domain.repository

import com.enesselcuk.moviesui.data.model.response.DetailResponse
import com.enesselcuk.moviesui.data.model.response.MoviesResponse
import com.enesselcuk.moviesui.data.model.response.TvDetailResponse
import com.enesselcuk.moviesui.util.NetworkResult
import kotlinx.coroutines.flow.Flow

interface ReposLocal {
    suspend fun getLiked():Flow<NetworkResult<List<DetailResponse>>>
    suspend fun insertLiked(moviesResponse: DetailResponse):Flow<NetworkResult<Any>>
    suspend fun delete(id:Int):Int

    suspend fun getTvLiked():Flow<NetworkResult<List<TvDetailResponse>>>
    suspend fun insertTvLiked(moviesResponse: TvDetailResponse):Flow<NetworkResult<Any>>
    suspend fun deleteTv(id:Int):Int
}