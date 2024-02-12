package com.enesselcuk.moviesui.domain.useCase.actor

import com.enesselcuk.moviesui.domain.repository.Repos
import com.enesselcuk.moviesui.data.model.response.ActorDetailResponse
import com.enesselcuk.moviesui.util.NetworkResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ActorDetailUseCase @Inject constructor(
    private val repos: Repos
) {
    suspend operator fun invoke(id: Int, language: String):Flow<NetworkResult<ActorDetailResponse>> {
       return repos.getActorDetail(id, language)
    }
}