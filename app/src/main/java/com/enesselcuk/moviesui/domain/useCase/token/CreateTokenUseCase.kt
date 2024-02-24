package com.enesselcuk.moviesui.domain.useCase.token

import com.enesselcuk.moviesui.data.model.authresponse.CreateRequestToken
import com.enesselcuk.moviesui.domain.repository.Repos
import com.enesselcuk.moviesui.util.NetworkResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CreateTokenUseCase @Inject constructor(private val repos: Repos) {
    suspend operator fun invoke(): Flow<NetworkResult<CreateRequestToken>> {
       return repos.createToken()
    }
}