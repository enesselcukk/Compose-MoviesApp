package com.enesselcuk.moviesui.data.repository

import androidx.paging.PagingData
import com.enesselcuk.moviesui.data.model.authresponse.CreateResponseToken
import com.enesselcuk.moviesui.data.model.authresponse.LoginResponse
import com.enesselcuk.moviesui.data.model.request.LoginRequest
import com.enesselcuk.moviesui.data.model.response.AccountDetailsResponse
import com.enesselcuk.moviesui.data.model.response.ActorDetailResponse
import com.enesselcuk.moviesui.data.model.response.ActorMoviesResponse
import com.enesselcuk.moviesui.data.model.response.ActorTvResponse
import com.enesselcuk.moviesui.data.model.response.DetailResponse
import com.enesselcuk.moviesui.data.model.response.MoviesPeople
import com.enesselcuk.moviesui.data.model.response.MoviesResponse
import com.enesselcuk.moviesui.data.model.response.Result
import com.enesselcuk.moviesui.data.model.response.TrendingResponse
import com.enesselcuk.moviesui.data.model.response.TvDetailResponse
import com.enesselcuk.moviesui.data.model.response.TvRecommendationsResponse
import com.enesselcuk.moviesui.domain.repository.Repos
import com.enesselcuk.moviesui.util.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeRepositoryImpl : Repos {
    override suspend fun getMovies(
        movies: String?, language: String?, page: Int
    ): Flow<NetworkResult<MoviesResponse>> = flow {
        emit(NetworkResult.Success(fakeMoviesResponse))
    }

    override suspend fun getMovie(movies: String?, language: String?): Flow<PagingData<Result>> {
        TODO("Not yet implemented")
    }

    override suspend fun getSearchMovies(
        language: String?, page: Int?, query: String?
    ): Flow<NetworkResult<MoviesResponse>> {
        TODO("Not yet implemented")
    }

    override suspend fun getSearchPeople(
        language: String?, page: Int?, query: String?
    ): Flow<NetworkResult<MoviesPeople>> {
        TODO("Not yet implemented")
    }

    override suspend fun getMoviesTrending(): Flow<NetworkResult<TrendingResponse>> {
        TODO("Not yet implemented")
    }

    override suspend fun getDetails(
        movieId: Int?, language: String?
    ): Flow<NetworkResult<DetailResponse>> {
        TODO("Not yet implemented")
    }

    override suspend fun getRecommended(
        movies: Int?, page: Int?, language: String?
    ): Flow<NetworkResult<MoviesResponse>> {
        TODO("Not yet implemented")
    }

    override suspend fun getActorDetail(
        id: Int, language: String?
    ): Flow<NetworkResult<ActorDetailResponse>> {
        TODO("Not yet implemented")
    }

    override suspend fun getActorMovies(
        id: Int, language: String?
    ): Flow<NetworkResult<ActorMoviesResponse>> {
        TODO("Not yet implemented")
    }

    override suspend fun getActorTv(
        id: Int, language: String?
    ): Flow<NetworkResult<ActorTvResponse>> {
        TODO("Not yet implemented")
    }

    override suspend fun getTvDetail(
        id: Int, language: String?
    ): Flow<NetworkResult<TvDetailResponse>> {
        TODO("Not yet implemented")
    }

    override suspend fun getTvRecommendations(
        id: Int, language: String?, page: Int?
    ): Flow<NetworkResult<TvRecommendationsResponse>> {
        TODO("Not yet implemented")
    }

    override suspend fun createToken(): Flow<NetworkResult<CreateResponseToken>> {
        TODO("Not yet implemented")
    }

    override suspend fun loginRequest(loginRequest: LoginRequest): Flow<NetworkResult<LoginResponse>> {
        TODO("Not yet implemented")
    }

    override suspend fun accountDetails(): Flow<NetworkResult<AccountDetailsResponse>> {
        TODO("Not yet implemented")
    }

    companion object MoviesList {
        private val resultFake = arrayListOf(Result(1, false, "", listOf(), "",
        "", "", 1.1, "", "", "",
        false, 1.1, 1))
        val fakeMoviesResponse = MoviesResponse(1, 1, resultFake, 1, 1)
        
    }

}

