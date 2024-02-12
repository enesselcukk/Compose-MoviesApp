package com.enesselcuk.moviesui.data.remote

import com.enesselcuk.moviesui.data.model.authresponse.CreateRequestToken
import com.enesselcuk.moviesui.data.model.response.ActorDetailResponse
import com.enesselcuk.moviesui.data.model.response.ActorMoviesResponse
import com.enesselcuk.moviesui.data.model.response.ActorTvResponse
import com.enesselcuk.moviesui.data.model.response.DetailResponse
import com.enesselcuk.moviesui.data.model.response.MoviesPeople
import com.enesselcuk.moviesui.data.model.response.MoviesResponse
import com.enesselcuk.moviesui.data.model.response.TrendingResponse
import com.enesselcuk.moviesui.data.model.response.TvDetailResponse
import com.enesselcuk.moviesui.data.model.response.TvRecommendationsResponse
import com.enesselcuk.moviesui.util.Constant.API_KEY
import com.enesselcuk.moviesui.util.NetworkResult
import retrofit2.Response
import retrofit2.http.*

interface MoviesService {
    @GET("3/movie/{title}")
    suspend fun getMovies(
        @Path("title") title: String? = null,
        @Query("api_key") api_key: String = API_KEY,
        @Query("language") language: String? = null,
        @Query("page") page: Int? = null,
    ): MoviesResponse

    @GET("3/trending/all/day")
    suspend fun getMoviesTrending(
        @Query("api_key") api_key: String = API_KEY,
    ): TrendingResponse

    @GET("3/search/movie")
    suspend fun getSearchMovies(
        @Query("api_key") api_key: String = API_KEY,
        @Query("language") language: String? = null,
        @Query("query") query: String? = null,
        @Query("page") page: Int? = null,
    ): MoviesResponse

    @GET("3/search/person")
    suspend fun getSearchPeople(
        @Query("api_key") api_key: String = API_KEY,
        @Query("language") language: String? = null,
        @Query("query") query: String? = null,
        @Query("page") page: Int? = null,
    ): MoviesPeople

    @GET("3/movie/{movie_id}")
    suspend fun getDetail(
        @Path("movie_id") movie_id: Int? = null,
        @Query("language") language: String? = null,
        @Query("api_key") api_key: String = API_KEY,
    ): DetailResponse

    @GET("3/movie/{id}/recommendations")
    suspend fun getRecommended(
        @Path("id") movie_id: Int? = null,
        @Query("language") language: String? = null,
        @Query("page") page: Int? = null,
        @Query("api_key") api_key: String = API_KEY,
    ): MoviesResponse

    @GET("3/person/{id}")
    suspend fun getActorDetail(
        @Path("id") actor_id: Int? = null,
        @Query("language") language: String? = null,
        @Query("api_key") api_key: String = API_KEY,
    ): ActorDetailResponse

    @GET("3/person/{id}/movie_credits")
    suspend fun getActorMoviesDetail(
        @Path("id") actor_id: Int? = null,
        @Query("language") language: String? = null,
        @Query("api_key") api_key: String = API_KEY,
    ): ActorMoviesResponse

    @GET("3/person/{id}/tv_credits")
    suspend fun getActorTvDetail(
        @Path("id") actor_id: Int? = null,
        @Query("language") language: String? = null,
        @Query("api_key") api_key: String = API_KEY,
    ): ActorTvResponse

    @GET("3/tv/{id}")
    suspend fun getTvDetail(
        @Path("id") id: Int? = null,
        @Query("language") language: String? = null,
        @Query("api_key") api_key: String = API_KEY,
    ): TvDetailResponse

    @GET("3/tv/{id}/recommendations")
    suspend fun getTvRecommendations(
        @Path("id") id: Int? = null,
        @Query("language") language: String? = null,
        @Query("page") page: Int? = null,
        @Query("api_key") api_key: String = API_KEY,
    ): TvRecommendationsResponse


    @Headers("accept : application/json")
    @GET("3/authentication/token/new")
    suspend fun createToken():NetworkResult<Response<CreateRequestToken>>


}
