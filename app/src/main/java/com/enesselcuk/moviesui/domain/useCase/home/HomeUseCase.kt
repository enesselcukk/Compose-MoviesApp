package com.enesselcuk.moviesui.domain.useCase.home

import com.enesselcuk.moviesui.domain.repository.Repos
import com.enesselcuk.moviesui.data.model.response.MoviesResponse
import com.enesselcuk.moviesui.di.DefaultDispatcher
import com.enesselcuk.moviesui.domain.base.BaseUiSate
import com.enesselcuk.moviesui.domain.base.BaseUseCase
import com.enesselcuk.moviesui.util.NetworkResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class HomeUseCase @Inject constructor(
    private val repos: Repos,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default
) : BaseUseCase<HomeUseCase.InputParams, MoviesResponse> {
    override suspend fun execute(input: InputParams?): BaseUiSate<MoviesResponse> {
        var uiState: BaseUiSate<MoviesResponse> = BaseUiSate.Initial
        withContext(defaultDispatcher) {
            val homeMovies = repos.getMovies(input?.title, input?.language, input?.page ?: 0)
            homeMovies.collect { moviesResponse ->
                uiState = when (moviesResponse) {
                    is NetworkResult.Loading -> {
                        BaseUiSate.Loading(moviesResponse.isLoading)
                    }

                    is NetworkResult.Success -> {
                        BaseUiSate.Success(moviesResponse.data)
                    }

                    is NetworkResult.Error -> {
                        BaseUiSate.Failure(moviesResponse.message)
                    }
                }
            }
        }
        return uiState
    }

    data class InputParams(
        val title: String? = null,
        val language: String? = null,
        val page: Int? = null,
    )
}

