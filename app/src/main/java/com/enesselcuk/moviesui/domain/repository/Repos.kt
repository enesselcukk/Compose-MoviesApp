package com.enesselcuk.moviesui.domain.repository

import androidx.paging.PagingData
import com.enesselcuk.moviesui.data.model.authresponse.CreateRequestToken
import com.enesselcuk.moviesui.data.model.request.LoginRequest
import com.enesselcuk.moviesui.data.model.response.ActorDetailResponse
import com.enesselcuk.moviesui.data.model.response.ActorMoviesResponse
import com.enesselcuk.moviesui.data.model.response.ActorTvResponse
import com.enesselcuk.moviesui.data.model.response.DetailResponse
import com.enesselcuk.moviesui.data.model.response.LoginResponse
import com.enesselcuk.moviesui.data.model.response.MoviesPeople
import com.enesselcuk.moviesui.data.model.response.MoviesResponse
import com.enesselcuk.moviesui.data.model.response.TrendingResponse
import com.enesselcuk.moviesui.data.model.response.TvDetailResponse
import com.enesselcuk.moviesui.data.model.response.TvRecommendationsResponse
import com.enesselcuk.moviesui.util.NetworkResult

import kotlinx.coroutines.flow.Flow

interface Repos {
    suspend fun getMovies(movies: String?, language: String?, page: Int): Flow<NetworkResult<MoviesResponse>>

    suspend fun getMovie(movies: String?, language: String?): Flow<PagingData<com.enesselcuk.moviesui.data.model.response.Result>>

    suspend fun getSearchMovies(language: String?, page: Int?, query: String?): Flow<NetworkResult<MoviesResponse>>

    suspend fun getSearchPeople(language: String?, page: Int?, query: String?): Flow<NetworkResult<MoviesPeople>>

    suspend fun getMoviesTrending(): Flow<NetworkResult<TrendingResponse>>

    suspend fun getDetails(movie_id: Int? = null, language: String? = null): Flow<NetworkResult<DetailResponse>>

    suspend fun getRecommended(movies: Int?, page: Int?, language: String?): Flow<NetworkResult<MoviesResponse>>

    suspend fun getActorDetail(id: Int, language: String?): Flow<NetworkResult<ActorDetailResponse>>
    suspend fun getActorMovies(id:Int,language: String?):Flow<NetworkResult<ActorMoviesResponse>>
    suspend fun getActorTv(id:Int,language: String?):Flow<NetworkResult<ActorTvResponse>>
    suspend fun getTvDetail(id:Int,language: String?):Flow<NetworkResult<TvDetailResponse>>
    suspend fun getTvRecommendations(id: Int,language: String?,page: Int?):Flow<NetworkResult<TvRecommendationsResponse>>

    suspend fun createToken():Flow<NetworkResult<CreateRequestToken>>

    suspend fun loginRequest(loginRequest: LoginRequest):Flow<NetworkResult<LoginResponse>>


}