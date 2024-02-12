package com.enesselcuk.moviesui.domain.useCase.tvDetail

import com.enesselcuk.moviesui.domain.repository.Repos
import com.enesselcuk.moviesui.data.model.response.TvDetailResponse
import com.enesselcuk.moviesui.util.NetworkResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TvDetailUseCase @Inject constructor(private val repos: Repos) {

    suspend operator fun invoke(id: Int, language: String) : Flow<NetworkResult<TvDetailResponse>> {
        return repos.getTvDetail(id, language)
    }
}