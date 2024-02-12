package com.enesselcuk.moviesui.domain.useCase.tvDetail

import com.enesselcuk.moviesui.domain.repository.ReposLocal
import com.enesselcuk.moviesui.data.model.response.TvDetailResponse
import com.enesselcuk.moviesui.util.NetworkResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TvLikedUseCase @Inject constructor(private val repos: ReposLocal) {

    suspend operator fun invoke(tvDetailResponse: TvDetailResponse) :  Flow<NetworkResult<Any>> {
        return repos.insertTvLiked(tvDetailResponse)
    }
}