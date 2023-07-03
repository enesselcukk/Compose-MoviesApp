package com.enesselcuk.moviesui.repos.reposRemote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.enesselcuk.moviesui.source.model.response.*
import com.enesselcuk.moviesui.source.pagingData.PagingMovies
import com.enesselcuk.moviesui.source.remote.MoviesService
import com.enesselcuk.moviesui.util.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val remoteDataSource: MoviesService,
) : Repos {
    override suspend fun getMovies(
        movies: String?,
        language: String?,
        page: Int
    ): Flow<NetworkResult<MoviesResponse>> = flow {
        emit(NetworkResult.Loading())
        val response = remoteDataSource.getMovies(title = movies, language = language, page = page)
        emit(NetworkResult.Success(response))

    }.flowOn(Dispatchers.IO).catch { emit(NetworkResult.Error(it.message)) }


    override suspend fun getMovie(
        movies: String?,
        language: String?
    ): Flow<PagingData<Result>> {
        val pageMovies = {
            PagingMovies(
                remoteApi = remoteDataSource,
                categoryName = movies,
                language = language
            )
        }
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = true,
                prefetchDistance = 2
            ),
            pagingSourceFactory = pageMovies
        ).flow
    }

    override suspend fun getMoviesTrending(): Flow<NetworkResult<TrendingResponse>> = flow {
        emit(NetworkResult.Loading())
        val response = remoteDataSource.getMoviesTrending()
        emit(NetworkResult.Success(response))
    }.flowOn(Dispatchers.IO).catch { emit(NetworkResult.Error(it.message)) }

    override suspend fun getDetails(
        movie_id: Int?,
        language: String?
    ): Flow<NetworkResult<DetailResponse>> = flow {
        emit(NetworkResult.Loading())
        val response = remoteDataSource.getDetail(movie_id, language)
        emit(NetworkResult.Success(response))
    }.flowOn(Dispatchers.IO).catch { emit(NetworkResult.Error(it.message)) }


    override suspend fun getSearchMovies(
        language: String?,
        page: Int?,
        query: String?
    ): Flow<NetworkResult<MoviesResponse>> = flow {
        emit(NetworkResult.Loading())
        val response =
            remoteDataSource.getSearchMovies(language = language, query = query, page = page)
        emit(NetworkResult.Success(response))
    }.flowOn(Dispatchers.IO).catch { emit(NetworkResult.Error(it.message)) }

    override suspend fun getSearchPeople(
        language: String?,
        page: Int?,
        query: String?
    ): Flow<NetworkResult<MoviesPeople>> = flow {
        emit(NetworkResult.Loading())
        val response =
            remoteDataSource.getSearchPeople(language = language, page = page, query = query)
        emit(NetworkResult.Success(response))
    }.catch { emit(NetworkResult.Error(it.message)) }.flowOn(Dispatchers.IO)

    override suspend fun getRecommended(
        movies: Int?,
        page: Int?,
        language: String?
    ): Flow<NetworkResult<MoviesResponse>> = flow {
        emit(NetworkResult.Loading())
        val response = remoteDataSource.getRecommended(movie_id = movies, page = page, language = language)
        emit(NetworkResult.Success(response))
    }.catch { emit(NetworkResult.Error(it.message)) }.flowOn(Dispatchers.IO)

    override suspend fun getActorDetail(
        id: Int,
        language: String?
    ): Flow<NetworkResult<ActorDetailResponse>> = flow {
        emit(NetworkResult.Loading())
        val response = remoteDataSource.getActorDetail(actor_id = id, language = language)
        emit(NetworkResult.Success(response))
    }.catch { emit(NetworkResult.Error(it.message)) }.flowOn(Dispatchers.IO)

    override suspend fun getActorMovies(
        id: Int,
        language: String?
    ): Flow<NetworkResult<ActorMoviesResponse>> = flow {
        emit(NetworkResult.Loading())
        val response = remoteDataSource.getActorMoviesDetail(actor_id = id, language = language)
        emit(NetworkResult.Success(response))
    }.catch { emit(NetworkResult.Error(it.message)) }.flowOn(Dispatchers.IO)

    override suspend fun getActorTv(
        id: Int,
        language: String?
    ): Flow<NetworkResult<ActorTvResponse>> = flow {
        emit(NetworkResult.Loading())
        val response = remoteDataSource.getActorTvDetail(actor_id = id, language = language)
        emit(NetworkResult.Success(response))
    }.catch { emit(NetworkResult.Error(it.message)) }.flowOn(Dispatchers.IO)

    override suspend fun getTvDetail(
        id: Int,
        language: String?
    ): Flow<NetworkResult<TvDetailResponse>> = flow {
        emit(NetworkResult.Loading())
        val response = remoteDataSource.getTvDetail(id = id, language = language)
        emit(NetworkResult.Success(response))
    }.catch { emit(NetworkResult.Error(it.message)) }.flowOn(Dispatchers.IO)

    override suspend fun getTvRecommendations(
        id: Int,
        language: String?,
        page: Int?
    ): Flow<NetworkResult<TvRecommendationsResponse>> = flow {
            emit(NetworkResult.Loading())
            val response = remoteDataSource.getTvRecommendations(id = id, language = language, page = page)
            emit(NetworkResult.Success(response))
        }.catch { emit(NetworkResult.Error(it.message)) }.flowOn(Dispatchers.IO)




    companion object {
        const val NETWORK_PAGE_SIZE = 20
    }
}