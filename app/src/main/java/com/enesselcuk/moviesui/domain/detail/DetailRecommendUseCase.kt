package com.enesselcuk.moviesui.domain.detail

import com.enesselcuk.moviesui.repos.reposRemote.Repos
import com.enesselcuk.moviesui.source.model.response.MoviesResponse
import com.enesselcuk.moviesui.util.NetworkResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DetailRecommendUseCase @Inject constructor(private val repos: Repos) {
    suspend operator fun invoke(id: Int?, page: Int?, language: String?):Flow<NetworkResult<MoviesResponse>>{
        return repos.getRecommended(id,page, language)
    }
}