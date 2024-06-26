package com.enesselcuk.moviesui.data.repository

import com.enesselcuk.moviesui.data.model.response.DetailResponse
import com.enesselcuk.moviesui.data.model.response.Genre
import com.enesselcuk.moviesui.data.model.response.ProductionCompany
import com.enesselcuk.moviesui.data.model.response.ProductionCountry
import com.enesselcuk.moviesui.data.model.response.SpokenLanguage
import com.enesselcuk.moviesui.data.model.response.TvDetailResponse
import com.enesselcuk.moviesui.domain.repository.ReposLocal
import com.enesselcuk.moviesui.util.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeLocalRepositoryImpl : ReposLocal {
    override suspend fun getLiked(): Flow<NetworkResult<List<DetailResponse>>> = flow {
        emit(NetworkResult.Success(fakeDetailResponseList))
    }

    override suspend fun insertLiked(moviesResponse: DetailResponse): Flow<NetworkResult<Any>> = flow{
        emit(NetworkResult.Success(1))
    }

    override suspend fun delete(id: Int): Int {
        TODO("Not yet implemented")
    }

    override suspend fun getTvLiked(): Flow<NetworkResult<List<TvDetailResponse>>> {
        TODO("Not yet implemented")
    }

    override suspend fun insertTvLiked(moviesResponse: TvDetailResponse): Flow<NetworkResult<Any>> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteTv(id: Int): Int {
        TODO("Not yet implemented")
    }

    companion object FakeLocalMoviesList {
        private val fakeGenre = arrayListOf(Genre(1, "name"))
        private val fakeProductionCompany = arrayListOf(ProductionCompany(1, "", "", ""))
        private val fakeProductionCountry = arrayListOf(ProductionCountry("", ""))
        private val fakeSpokenLanguage = arrayListOf(SpokenLanguage("", "", ""))
        val fakeDetailResponseList = arrayListOf( DetailResponse(
            1, false, "", "",
            1, fakeGenre, "", "", "", "", "", 1.1,
            "", fakeProductionCompany, fakeProductionCountry, "", 1, 1, fakeSpokenLanguage, ""
        ))
    }
}