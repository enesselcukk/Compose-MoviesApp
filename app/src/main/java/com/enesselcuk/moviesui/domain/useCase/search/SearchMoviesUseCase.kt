package com.enesselcuk.moviesui.domain.useCase.search

import com.enesselcuk.moviesui.domain.repository.Repos
import com.enesselcuk.moviesui.data.model.response.MoviesResponse
import com.enesselcuk.moviesui.util.NetworkResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchMoviesUseCase @Inject constructor(private val repos: Repos) {
    suspend operator fun invoke(language: String, page: Int, query: String) : Flow<NetworkResult<MoviesResponse>> {
        return repos.getSearchMovies(language, page, query)
    }
}