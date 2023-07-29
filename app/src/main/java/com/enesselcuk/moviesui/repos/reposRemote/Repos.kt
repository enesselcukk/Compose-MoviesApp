package com.enesselcuk.moviesui.repos.reposRemote

import androidx.paging.PagingData
import com.enesselcuk.moviesui.source.model.authresponse.CreateRequestToken
import com.enesselcuk.moviesui.source.model.response.*
import com.enesselcuk.moviesui.util.NetworkResult

import kotlinx.coroutines.flow.Flow
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import kotlin.Result

interface Repos {
    suspend fun getMovies(movies: String?, language: String?, page: Int): Flow<NetworkResult<MoviesResponse>>

    suspend fun getMovie(movies: String?, language: String?): Flow<PagingData<com.enesselcuk.moviesui.source.model.response.Result>>

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
    suspend fun getPlayerMovies(id:Int):Flow<NetworkResult<MoviesVideoResponse>>

  //  suspend fun createToken():Flow<NetworkResult<Response<CreateRequestToken>>>


}