package com.enesselcuk.moviesui.domain.useCase.detail

import com.enesselcuk.moviesui.domain.repository.Repos
import com.enesselcuk.moviesui.data.model.response.DetailResponse
import com.enesselcuk.moviesui.util.NetworkResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DetailUseCase @Inject constructor(private val repos: Repos) {
    suspend operator fun invoke(movieId: Int?, language: String):Flow<NetworkResult<DetailResponse>>{
        return repos.getDetails(movieId,language)
    }
}