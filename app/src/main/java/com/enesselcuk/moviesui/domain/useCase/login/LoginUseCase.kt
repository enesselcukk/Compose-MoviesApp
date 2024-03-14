package com.enesselcuk.moviesui.domain.useCase.login

import com.enesselcuk.moviesui.data.model.request.LoginRequest
import com.enesselcuk.moviesui.data.model.authresponse.LoginResponse
import com.enesselcuk.moviesui.domain.repository.Repos
import com.enesselcuk.moviesui.util.NetworkResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val repos: Repos) {

    suspend operator fun invoke(loginRequest: LoginRequest):Flow<NetworkResult<LoginResponse>>{
        return repos.loginRequest(loginRequest)
    }
}