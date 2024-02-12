package com.enesselcuk.moviesui.domain.useCase.actor

import com.enesselcuk.moviesui.domain.repository.Repos
import com.enesselcuk.moviesui.data.model.response.ActorTvResponse
import com.enesselcuk.moviesui.util.NetworkResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ActorTvUseCase @Inject constructor(
    private val repos: Repos
) {
    suspend operator fun invoke(id: Int, language: String):Flow<NetworkResult<ActorTvResponse>> {
       return repos.getActorTv(id, language)
    }
}