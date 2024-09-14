package com.enesselcuk.moviesui.domain.useCase.home

import com.enesselcuk.moviesui.domain.repository.Repos
import com.enesselcuk.moviesui.data.model.response.MoviesResponse
import com.enesselcuk.moviesui.util.NetworkResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class HomeUseCase @Inject constructor(private val repos: Repos) {

    suspend operator fun invoke(
        title: String,
        language: String,
        page: Int
    ): Flow<NetworkResult<MoviesResponse>> {
        return repos.getMovies(title, language, page)
    }

}

