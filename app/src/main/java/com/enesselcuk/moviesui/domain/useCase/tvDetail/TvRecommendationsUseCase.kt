package com.enesselcuk.moviesui.domain.useCase.tvDetail

import com.enesselcuk.moviesui.domain.repository.Repos
import com.enesselcuk.moviesui.data.model.response.TvRecommendationsResponse
import com.enesselcuk.moviesui.util.NetworkResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TvRecommendationsUseCase @Inject constructor(private val repos: Repos) {

    suspend operator fun invoke(id: Int, language: String, page: Int) :  Flow<NetworkResult<TvRecommendationsResponse>> {
        return repos.getTvRecommendations(id,language,page)
    }
}