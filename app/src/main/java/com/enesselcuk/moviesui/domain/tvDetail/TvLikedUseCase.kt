package com.enesselcuk.moviesui.domain.tvDetail

import com.enesselcuk.moviesui.repos.reposLocal.ReposLocal
import com.enesselcuk.moviesui.repos.reposRemote.Repos
import com.enesselcuk.moviesui.source.model.response.TvDetailResponse
import com.enesselcuk.moviesui.util.NetworkResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TvLikedUseCase @Inject constructor(private val repos: ReposLocal) {

    suspend operator fun invoke(tvDetailResponse: TvDetailResponse) :  Flow<NetworkResult<Any>> {
        return repos.insertTvLiked(tvDetailResponse)
    }
}